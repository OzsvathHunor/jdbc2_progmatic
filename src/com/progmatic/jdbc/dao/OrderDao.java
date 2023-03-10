package com.progmatic.jdbc.dao;

import com.progmatic.jdbc.DBEngine;
import com.progmatic.jdbc.model.Order;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OrderDao implements Dao<Order> {

    DBEngine engine;
    CourierDao courierDao;
    ClientDao clientDao;

    OrderItemDao orderItemDao;

    public OrderDao(DBEngine engine, CourierDao courierDao, ClientDao clientDao, OrderItemDao orderItemDao) {
        this.engine = engine;
        this.clientDao = clientDao;
        this.courierDao = courierDao;
        this.orderItemDao = orderItemDao;
    }


    @Override
    public Order get(long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        List<Order> all = new LinkedList<>();

        try (
                Statement s = engine.getConnection().createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM rendeles;");
        ) {
            while (rs.next()) {
                all.add(resultToOrder(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    private Order resultToOrder(ResultSet rs) throws SQLException {
        return new Order(
            rs.getLong("razon"),
            clientDao.get(rs.getLong("vazon")),
            courierDao.get(rs.getLong("fazon")),
            orderItemDao.getAll(rs.getLong("razon")),
            rs.getTimestamp("idopont").toLocalDateTime()
        );
    }

    @Override
    public void save(Order order) {

        try (
                PreparedStatement s = engine.getConnection().prepareStatement("INSERT INTO rendeles " +
                        "values(?, ?, ?, ?)");
        ){
            s.setLong(1, order.oid());
            s.setLong(2, order.client().cid());
            s.setLong(3, order.courier().cid());
            s.setTime(4, Time.valueOf(order.orderedAt().toLocalTime()));
            s.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order order, String[] params) {

    }

    @Override
    public void delete(Order order) {

    }
}

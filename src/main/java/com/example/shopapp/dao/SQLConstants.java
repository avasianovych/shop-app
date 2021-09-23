package com.example.shopapp.dao;

public class SQLConstants {

    private SQLConstants() {
    }

    public static final String ADD_NEW_USER = "insert into bikeshopdb.user (name , surname, role_id, login, password, isBlock) values (?, ?, (select role.id from bikeshopdb.role where role.name='user'), ?, ?, '0')";
    public static final String UNBLOCK_USER = "update user set isBlock='0' where id=?";
    public static final String BLOCK_USER = "update user set isBlock='1' where id=?";
    public static final String FIND_ALL_USERS = "select us.id, us.name, us.surname, us.role_id, us.login, us.isBlock from user us inner join role rl on rl.id = us.role_id where rl.name='user'";
    public static final String FIND_USER_BY_LOGIN = "SELECT * from USER where login = ?";
    public static final String UPDATE_ORDER_STATE_BY_ORDER_ID_AND_STATE_NAME = "update bikeshopdb.order od set od.state_id=(select id from bikeshopdb.state st where st.name=?) where od.id=?";
    public static final String FIND_ORDER_STATE_BY_ORDER_ID = "select st.name from bikeshopdb.order od inner join bikeshopdb.state st on st.id = od.state_id where od.id = ?";
    public static final String FIND_ALL_ORDERS = "SELECT od.id, od.user_id, od.state_id, od.total_price, od.create_date, st.name, us.login from bikeshopdb.order od inner join bikeshopdb.state st on st.id = od.state_id inner join bikeshopdb.user us on us.id = od.user_id order by od.create_date desc";
    public static final String DELETE_PRODUCT_BY_ID = "delete from bikeshopdb.product where id = ?";
    public static final String ADD_NEW_PRODUCT = "insert into bikeshopdb.product (category_id, name, color, price, made_in, description, timestamp) values ((select id from bikeshopdb.category where name=?), ?, ? , ?, ? , ?, ?)";
    public static final String FIND_PRODUCT_BY_ID = "SELECT * from PRODUCT where id=?";
    public static final String UPDATE_PRODUCT_BY_ID = "update bikeshopdb.product pt set pt.name=?, pt.description=?, pt.color=?, pt.price=?, pt.made_in=?, pt.category_id=(select id from bikeshopdb.category ct where ct.name=?) where pt.id=?";
    public static final String FIND_ALL_CATEGORIES = "SELECT * from CATEGORY";
    public static final String FIND_ALL_PRODUCTS = "SELECT * from PRODUCT order by name asc";
    public static final String FIND_ROLE_BY_USER_LOGIN = "SELECT ROLE.ID, ROLE.NAME from ROLE LEFT JOIN USER ON USER.ROLE_ID = ROLE.ID WHERE USER.LOGIN = ?";
    public static final String FIND_CATEGORY_ID_BY_NAME = "SELECT * from CATEGORY WHERE NAME=?";
    public static final String FIND_CART_PRODUCTS = "SELECT * from PRODUCT where id = ?";
    public static final String REGISTER_ORDER = "INSERT INTO bikeshopdb.order (user_id, state_id, total_price, create_date) values (?,(select id from state where name='registered'),?,?)";
    public static final String ADD_TO_ORDER_HAS_PRODUCTS = "INSERT INTO bikeshopdb.order_has_product (Order_id, Product_id, quantity) values (?,?,?)";
    public static final String FIND_ORDERS_BY_USER_ID = "select od.id, od.user_id, od.state_id, od.total_price, od.create_date, st.name from bikeshopdb.order od inner join bikeshopdb.state st on st.id = od.state_id where od.user_id=? order by od.id desc";
    public static final String FIND_ORDER_ITEMS_BY_ORDER_ID = "select ot.Order_id, ot.quantity, ot.Product_id, pt.name, pt.color, pt.description, pt.price, pt.made_in from bikeshopdb.order_has_product ot inner join bikeshopdb.product pt on pt.id = ot.Product_id where ot.Order_id=?";
}

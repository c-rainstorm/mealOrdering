package com.chenswe.sellermanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chenswe.sellermanager.dao.sellerDAO;
import com.chenswe.sellermanager.entity.Food;
import com.chenswe.sellermanager.entity.Seller;
import com.chenswe.sellermanager.entity.SellerBrief;
import com.chenswe.util.DBUtil;

public class SellerDAOImpl implements sellerDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * close resultSet, preparedStatement, connection
     */
    private void closeAll() {
        try {

            if (resultSet != null)
                resultSet.close();

            if (preparedStatement != null)
                preparedStatement.close();

            if (connection != null)
                connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkRegistrationID(Seller seller) {
        boolean flag = false;
        String sql = "select registration_id from sellers where registration_id = ?";

        try {
            connection = DBUtil.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, seller.getRegistrationID());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return flag;
    }

    @Override
    public boolean checkPhone(Seller seller) {
        boolean flag = false;
        String sql = "select phone from sellers where phone = ?";

        try {
            connection = DBUtil.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, seller.getPhone());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return flag;
    }

    @Override
    public boolean addSeller(Seller seller) {
        boolean flag = false;
        String sql = "insert into sellers values(?,?,password(?),?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = DBUtil.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, seller.getRegistrationID());
            preparedStatement.setString(2, seller.getPhone());
            preparedStatement.setString(3, seller.getPassword());
            preparedStatement.setInt(4, seller.getStatus());
            preparedStatement.setString(5, seller.getStoreName());
            preparedStatement.setString(6, seller.getStoreAddress());
            preparedStatement.setString(7, seller.getCategory());
            preparedStatement.setString(8, seller.getDescription());
            preparedStatement.setString(9, seller.getAnnouncement());
            preparedStatement.setInt(10, seller.getDeliveryFee());
            preparedStatement.setInt(11, seller.getSoldNum());
            preparedStatement.setInt(12, seller.getFoodNum());
            preparedStatement.setString(13, seller.getStorePhotoAddr());
            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return flag;
    }

    @Override
    public boolean checkLoginInfo(Seller seller) {
        boolean flag = false;
        String sql = "select phone from sellers where phone = ? and password = password(?);";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, seller.getPhone());
            preparedStatement.setString(2, seller.getPassword());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                flag = true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return flag;
    }

    @Override
    public String getRegistrationID(Seller seller) {
        String id = null;
        String sql = "select registration_id from sellers where phone = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, seller.getPhone());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return id;
    }

    @Override
    public ArrayList<SellerBrief> getSellerBrief(String pageContent, int page) {
        if (pageContent.equals("全部分类"))
            pageContent = "%";

        ArrayList<SellerBrief> sellerBriefs = new ArrayList<>(20);
        String sql = "SELECT registration_id, store_name, store_photo_addr, delivery_fee, sold_num "
                +
                "FROM sellers " +
                "WHERE category LIKE ? and sellers.status = 1 " +
                "ORDER BY sold_num DESC " +
                "limit ? , 12;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, pageContent);
            preparedStatement.setInt(2, (page - 1) * 12);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SellerBrief sellerBrief = new SellerBrief();
                sellerBrief.setRegistrationID(resultSet.getString(1));
                sellerBrief.setStore_name(resultSet.getString(2));
                sellerBrief.setStore_photo(resultSet.getString(3));
                sellerBrief.setDeliveryFee(resultSet.getInt(4));
                sellerBrief.setSoldNum(resultSet.getInt(5));

                sellerBriefs.add(sellerBrief);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return sellerBriefs;
    }

    @Override
    public Seller getSellerDetail(Seller seller) {

        String sql = "select * from sellers where registration_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, seller.getRegistrationID());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                seller.setPhone(resultSet.getString("phone"));
                seller.setStatus(resultSet.getInt("status"));
                seller.setStorePhotoAddr(resultSet.getString("store_photo_addr"));
                seller.setStoreName(resultSet.getString("store_name"));
                seller.setStoreAddress(resultSet.getString("store_address"));
                seller.setCategory(resultSet.getString("category"));
                seller.setDescription(resultSet.getString("description"));
                seller.setAnnouncement(resultSet.getString("announcement"));
                seller.setDeliveryFee(resultSet.getInt("delivery_fee"));
                seller.setSoldNum(resultSet.getInt("sold_num"));
                seller.setFoodNum(resultSet.getInt("food_num"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return seller;
    }

    @Override
    public ArrayList<Food> getFoodsBrief(String registrationID, String pageContent, int page) {
        ArrayList<Food> foods = new ArrayList<>(20);

        String foodIDPattern = registrationID + "%";

        String sql = "SELECT * from foods where foodID like ? and status = 1 ";
        if (pageContent.equals("soldNumDown"))
            sql = sql + "ORDER BY soldNum desc, price desc limit ? , 12;";
        else if (pageContent.equals("soldNumUp"))
            sql = sql + "ORDER BY soldNum, price desc limit ? , 12;";
        else if (pageContent.equals("priceDown"))
            sql = sql + "ORDER BY price desc, soldNum desc limit ? , 12;";
        else if (pageContent.equals("priceUp"))
            sql = sql + "ORDER BY price, soldNum desc limit ? , 12;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, foodIDPattern);
            preparedStatement.setInt(2, (page - 1) * 12);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = new Food();

                food.setFoodID(resultSet.getString("foodID"));
                food.setName(resultSet.getString("name"));
                food.setImageAddr(resultSet.getString("imageAddr"));
                food.setPrice(resultSet.getFloat("price"));
                food.setSoldNum(resultSet.getInt("soldNum"));

                foods.add(food);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return foods;
    }

    @Override
    public Food getFoodBrief(Food food) {
        String sql = "select * from foods where foodID = ?;";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, food.getFoodID());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                food.setName(resultSet.getString("name"));
                food.setImageAddr(resultSet.getString("imageAddr"));
                food.setPrice(resultSet.getFloat("price"));
                food.setSoldNum(resultSet.getInt("soldNum"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return food;
    }

    @Override
    public int getStoreStatus(String registrationID) {
        int status = 0;

        String sql = "select sellers.status from sellers where registration_id = ?;";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, registrationID);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                status = resultSet.getInt("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return status;
    }

    @Override
    public void changeStoreStatus(String registrationID) {
        String sql = "update sellers set sellers.status = (0x1^sellers.status) where registration_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, registrationID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    @Override
    public boolean updateSellerInfo(Seller seller) {
        boolean flag = false;

        String sql = "UPDATE sellers set password = password(?), store_name = ?, category = ?," +
                "store_address = ?, description = ?, announcement = ?, delivery_fee = ? " +
                "WHERE registration_id = ?;";

        try {
            connection = DBUtil.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, seller.getPassword());
            preparedStatement.setString(2, seller.getStoreName());
            preparedStatement.setString(3, seller.getCategory());
            preparedStatement.setString(4, seller.getStoreAddress());
            preparedStatement.setString(5, seller.getDescription());
            preparedStatement.setString(6, seller.getAnnouncement());
            preparedStatement.setInt(7, seller.getDeliveryFee());
            preparedStatement.setString(8, seller.getRegistrationID());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return flag;
    }

    @Override
    public ArrayList<Food> getSellerFoods(String registrationID, int page) {
        ArrayList<Food> foods = new ArrayList<>();

        String sql = "SELECT * from foods where foodID LIKE ? ORDER BY LENGTH(foodID), foodID;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, registrationID + "%");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = new Food();

                food.setFoodID(resultSet.getString("foodID"));
                food.setName(resultSet.getString("name"));
                food.setImageAddr(resultSet.getString("imageAddr"));
                food.setPrice(resultSet.getFloat("price"));
                food.setSoldNum(resultSet.getInt("soldNum"));
                food.setStatus(resultSet.getInt("status"));

                foods.add(food);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return foods;
    }

    @Override
    public boolean modifyFoodInfo(Food food) {
        boolean success = false;
        String sql = "UPDATE foods set name = ?, imageAddr = ?, price = ? " +
                "WHERE foodID = ?;";

        try {
            connection = DBUtil.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getImageAddr());
            preparedStatement.setFloat(3, food.getPrice());
            preparedStatement.setString(4, food.getFoodID());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return success;
    }

    @Override
    public void changeFoodStatus(String foodID) {
        String sql = "update foods set foods.status = (0x1^foods.status) where foodID = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, foodID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    @Override
    public boolean deleteFood(String foodID) {
        boolean success = false;

        String sql = "delete from foods where foodID = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, foodID);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return success;
    }

    @Override
    public boolean increaseFoodNum(String registrationID) {
        boolean success = false;
        String sql = "update sellers set food_num = food_num + 1 where registration_id = ?";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, registrationID);

            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return success;
    }

    @Override
    public int getFoodNum(String registrationID) {
        int foodNum = 0;
        String sql = "select food_num from sellers where registration_id = ?";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, registrationID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                foodNum = resultSet.getInt("food_num");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return foodNum;
    }

    @Override
    public boolean addFood(Food food) {
        boolean success = false;

        String sql = "INSERT INTO foods VALUES(?,?,?,?,?,?);";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, food.getFoodID());
            preparedStatement.setString(2, food.getName());
            preparedStatement.setString(3, food.getImageAddr());
            preparedStatement.setFloat(4, food.getPrice());
            preparedStatement.setInt(5, food.getSoldNum());
            preparedStatement.setInt(6, food.getStatus());

            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return success;
    }

}

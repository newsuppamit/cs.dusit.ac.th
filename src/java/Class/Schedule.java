/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import static Class.ContentData.Option.add;
import static Class.ContentData.Option.all;
import static Class.ContentData.Option.edit;
import static Class.ContentData.Option.remove;
import static Class.ContentData.Option.show;
import static Class.ContentData.Option.some;
import Servlet.index;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import sun.misc.BASE64Decoder;

/**
 *
 * @author NewSuppamit
 */
public class Schedule {

    public static String getData(String option, String detail) {
        switch (ContentData.Option.valueOf(option)) {
            case show:
                return showDB(detail);
            case all:
                return showAllDB();
            case some:
                return showSomeDB(detail);
            default:
                return null;
        }
    }

    public static boolean setData(String option, String data) {
        switch (ContentData.Option.valueOf(option)) {
            case add:
                return addDB(data);
            case edit:
                return editDB(data);
            case remove:
                return removeDB(data);
            default:
                return false;
        }
    }

    private static String showDB(String detail) {
        try {
            JSONObject data = (JSONObject) JSONValue.parse(detail);
            JSONObject json = new JSONObject();
            JSONArray jarray = new JSONArray();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM schedule "
                    + "WHERE category = '" + data.get("category") + "' and status = '1' "
                    + "ORDER BY id_sch DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_sch", con.getString("id_sch"));
                jchil.put("category", con.getString("category"));
                jchil.put("title", con.getString("title"));
                jchil.put("file", con.getString("file"));
                jchil.put("status", con.getString("status"));
                jarray.add(jchil);
            }
            json.put("data", jarray);
            con.disconnect();
            return json.toString();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private static String showAllDB() {
        try {
            JSONObject json = new JSONObject();
            JSONArray jarray = new JSONArray();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM schedule "
                    + "ORDER BY id_sch DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_sch", con.getString("id_sch"));
                jchil.put("category", con.getString("category"));
                jchil.put("title", con.getString("title"));
                jchil.put("file", con.getString("file"));
                jchil.put("status", con.getString("status"));
                jarray.add(jchil);
            }
            json.put("data", jarray);
            con.disconnect();
            return json.toString();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private static String showSomeDB(String detail) {
        try {
            JSONObject data = (JSONObject) JSONValue.parse(detail);
            JSONObject json = new JSONObject();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM schedule "
                    + "WHERE id_sch = '" + data.get("id_sch") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_sch", con.getString("id_sch"));
                json.put("category", con.getString("category"));
                json.put("title", con.getString("title"));
                json.put("file", con.getString("file"));
                json.put("status", con.getString("status"));
            }
            con.disconnect();
            return json.toString();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private static boolean addDB(String data) {
        try {
            Connect con = new Connect();
            JSONObject json = (JSONObject) JSONValue.parse(data);
            con.connect();
            String select = "select max(id_sch) as id_sch from schedule";
            con.query(select);
            con.next();
            String id_sch = con.getString("id_sch");
            if (id_sch == null) {
                id_sch = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_sch = decimal_format.format(Integer.parseInt(id_sch) + 1);
            String file = (String) json.get("file");
            String filename = (String) json.get("filename");
            if (file != null) {
                String path = "file/schedule/";
                String[] filename_temp = filename.split("[.]");
                filename = filename_temp[filename_temp.length - 1];
                String[] datas = file.split("[,]");
                BASE64Decoder decoder = new BASE64Decoder();
                filename = path + "sch_" + id_sch + "." + filename;
                String base64 = datas[1];
                byte[] normal = decoder.decodeBuffer(base64);
                FileOutputStream fo = new FileOutputStream(json.get("path") + filename);
                fo.write(normal);
                fo.close();
            }
            String insert = "insert into schedule values('"
                    + id_sch + "','"
                    + json.get("category") + "','"
                    + json.get("title") + "','"
                    + filename + "','"
                    + json.get("status") + "')";
            if (con.insert(insert) > 0) {
                return true;
            }
            con.disconnect();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private static boolean editDB(String data) {
        try {
            Connect con = new Connect();
            JSONObject json = (JSONObject) JSONValue.parse(data);
            con.connect();
            String file = (String) json.get("file");
            String filename = (String) json.get("filename");
            if (file != null) {
                String select = "select file from schedule "
                        + "WHERE id_sch = '" + json.get("id_sch") + "'";
                con.query(select);
                if (con.next()) {
                    if (!"".equals(filename)) {
                        new File(json.get("path") + con.getString("file")).delete();
                    }
                }
                String path = "file/schedule/";
                String[] filename_temp = filename.split("[.]");
                filename = filename_temp[filename_temp.length - 1];
                String[] datas = file.split("[,]");
                BASE64Decoder decoder = new BASE64Decoder();
                filename = path + "sch_" + json.get("id_sch") + "." + filename;
                String base64 = datas[1];
                byte[] normal = decoder.decodeBuffer(base64);
                FileOutputStream fo = new FileOutputStream(json.get("path") + filename);
                filename = "file = '" + filename + "',";
                fo.write(normal);
                fo.close();
            }
            String update = "UPDATE schedule SET "
                    + "category = '" + json.get("category") + "',"
                    + "title = '" + json.get("title") + "',"
                    + filename
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_sch = '" + json.get("id_sch") + "'";
            if (con.update(update) > 0) {
                return true;
            }
            con.disconnect();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private static boolean removeDB(String data) {
        try {
            Connect con = new Connect();
            JSONObject json = (JSONObject) JSONValue.parse(data);
            con.connect();
            String select = "select file from schedule "
                    + "WHERE id_sch = '" + json.get("id_sch") + "'";
            con.query(select);
            if (con.next()) {
                String filename = con.getString("file");
                if (!"".equals(filename)) {
                    new File(json.get("path") + filename).delete();
                }
            }
            String delete = "delete from schedule "
                    + "WHERE id_sch = '" + json.get("id_sch") + "'";
            if (con.delete(delete) > 0) {
                return true;
            }
            con.disconnect();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
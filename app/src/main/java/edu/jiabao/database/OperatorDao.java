package edu.jiabao.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "OPERATOR".
*/
public class OperatorDao extends AbstractDao<Operator, Long> {

    public static final String TABLENAME = "OPERATOR";

    /**
     * Properties of entity Operator.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Operator_id = new Property(0, Long.class, "operator_id", true, "_id");
        public final static Property Operator_name = new Property(1, String.class, "operator_name", false, "OPERATOR_NAME");
        public final static Property Folder_id = new Property(2, Long.class, "folder_id", false, "FOLDER_ID");
        public final static Property User_id = new Property(3, Long.class, "user_id", false, "USER_ID");
        public final static Property Content_list = new Property(4, String.class, "content_list", false, "CONTENT_LIST");
    }

    private final StringConverter content_listConverter = new StringConverter();

    public OperatorDao(DaoConfig config) {
        super(config);
    }
    
    public OperatorDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"OPERATOR\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: operator_id
                "\"OPERATOR_NAME\" TEXT," + // 1: operator_name
                "\"FOLDER_ID\" INTEGER," + // 2: folder_id
                "\"USER_ID\" INTEGER," + // 3: user_id
                "\"CONTENT_LIST\" TEXT);"); // 4: content_list
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"OPERATOR\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Operator entity) {
        stmt.clearBindings();
 
        Long operator_id = entity.getOperator_id();
        if (operator_id != null) {
            stmt.bindLong(1, operator_id);
        }
 
        String operator_name = entity.getOperator_name();
        if (operator_name != null) {
            stmt.bindString(2, operator_name);
        }
 
        Long folder_id = entity.getFolder_id();
        if (folder_id != null) {
            stmt.bindLong(3, folder_id);
        }
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(4, user_id);
        }
 
        List content_list = entity.getContent_list();
        if (content_list != null) {
            stmt.bindString(5, content_listConverter.convertToDatabaseValue(content_list));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Operator entity) {
        stmt.clearBindings();
 
        Long operator_id = entity.getOperator_id();
        if (operator_id != null) {
            stmt.bindLong(1, operator_id);
        }
 
        String operator_name = entity.getOperator_name();
        if (operator_name != null) {
            stmt.bindString(2, operator_name);
        }
 
        Long folder_id = entity.getFolder_id();
        if (folder_id != null) {
            stmt.bindLong(3, folder_id);
        }
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(4, user_id);
        }
 
        List content_list = entity.getContent_list();
        if (content_list != null) {
            stmt.bindString(5, content_listConverter.convertToDatabaseValue(content_list));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Operator readEntity(Cursor cursor, int offset) {
        Operator entity = new Operator( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // operator_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // operator_name
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // folder_id
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // user_id
            cursor.isNull(offset + 4) ? null : content_listConverter.convertToEntityProperty(cursor.getString(offset + 4)) // content_list
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Operator entity, int offset) {
        entity.setOperator_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOperator_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFolder_id(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setUser_id(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setContent_list(cursor.isNull(offset + 4) ? null : content_listConverter.convertToEntityProperty(cursor.getString(offset + 4)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Operator entity, long rowId) {
        entity.setOperator_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Operator entity) {
        if(entity != null) {
            return entity.getOperator_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Operator entity) {
        return entity.getOperator_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

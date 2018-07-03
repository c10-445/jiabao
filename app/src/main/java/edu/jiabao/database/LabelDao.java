package edu.jiabao.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LABEL".
*/
public class LabelDao extends AbstractDao<Label, Integer> {

    public static final String TABLENAME = "LABEL";

    /**
     * Properties of entity Label.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Label_id = new Property(0, int.class, "label_id", true, "LABEL_ID");
        public final static Property Label_name = new Property(1, String.class, "label_name", false, "LABEL_NAME");
        public final static Property Label_type = new Property(2, int.class, "label_type", false, "LABEL_TYPE");
        public final static Property Label_parents = new Property(3, int.class, "label_parents", false, "LABEL_PARENTS");
        public final static Property Device_id = new Property(4, int.class, "device_id", false, "DEVICE_ID");
        public final static Property User_id = new Property(5, int.class, "user_id", false, "USER_ID");
    }


    public LabelDao(DaoConfig config) {
        super(config);
    }
    
    public LabelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LABEL\" (" + //
                "\"LABEL_ID\" INTEGER PRIMARY KEY NOT NULL ," + // 0: label_id
                "\"LABEL_NAME\" TEXT," + // 1: label_name
                "\"LABEL_TYPE\" INTEGER NOT NULL ," + // 2: label_type
                "\"LABEL_PARENTS\" INTEGER NOT NULL ," + // 3: label_parents
                "\"DEVICE_ID\" INTEGER NOT NULL ," + // 4: device_id
                "\"USER_ID\" INTEGER NOT NULL );"); // 5: user_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LABEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Label entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getLabel_id());
 
        String label_name = entity.getLabel_name();
        if (label_name != null) {
            stmt.bindString(2, label_name);
        }
        stmt.bindLong(3, entity.getLabel_type());
        stmt.bindLong(4, entity.getLabel_parents());
        stmt.bindLong(5, entity.getDevice_id());
        stmt.bindLong(6, entity.getUser_id());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Label entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getLabel_id());
 
        String label_name = entity.getLabel_name();
        if (label_name != null) {
            stmt.bindString(2, label_name);
        }
        stmt.bindLong(3, entity.getLabel_type());
        stmt.bindLong(4, entity.getLabel_parents());
        stmt.bindLong(5, entity.getDevice_id());
        stmt.bindLong(6, entity.getUser_id());
    }

    @Override
    public Integer readKey(Cursor cursor, int offset) {
        return cursor.getInt(offset + 0);
    }    

    @Override
    public Label readEntity(Cursor cursor, int offset) {
        Label entity = new Label( //
            cursor.getInt(offset + 0), // label_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // label_name
            cursor.getInt(offset + 2), // label_type
            cursor.getInt(offset + 3), // label_parents
            cursor.getInt(offset + 4), // device_id
            cursor.getInt(offset + 5) // user_id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Label entity, int offset) {
        entity.setLabel_id(cursor.getInt(offset + 0));
        entity.setLabel_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLabel_type(cursor.getInt(offset + 2));
        entity.setLabel_parents(cursor.getInt(offset + 3));
        entity.setDevice_id(cursor.getInt(offset + 4));
        entity.setUser_id(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Integer updateKeyAfterInsert(Label entity, long rowId) {
        return entity.getLabel_id();
    }
    
    @Override
    public Integer getKey(Label entity) {
        if(entity != null) {
            return entity.getLabel_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Label entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

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
 * DAO for table "TIMING".
*/
public class TimingDao extends AbstractDao<Timing, Long> {

    public static final String TABLENAME = "TIMING";

    /**
     * Properties of entity Timing.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Timing_id = new Property(0, Long.class, "timing_id", true, "_id");
        public final static Property Timing_name = new Property(1, String.class, "timing_name", false, "TIMING_NAME");
        public final static Property Is_on = new Property(2, boolean.class, "is_on", false, "IS_ON");
        public final static Property User_id = new Property(3, Long.class, "user_id", false, "USER_ID");
        public final static Property Operator_list = new Property(4, String.class, "operator_list", false, "OPERATOR_LIST");
        public final static Property Task_id_list = new Property(5, String.class, "task_id_list", false, "TASK_ID_LIST");
        public final static Property Time = new Property(6, java.util.Date.class, "time", false, "TIME");
    }

    private final StringConverter operator_listConverter = new StringConverter();
    private final StringConverter task_id_listConverter = new StringConverter();

    public TimingDao(DaoConfig config) {
        super(config);
    }
    
    public TimingDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TIMING\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: timing_id
                "\"TIMING_NAME\" TEXT," + // 1: timing_name
                "\"IS_ON\" INTEGER NOT NULL ," + // 2: is_on
                "\"USER_ID\" INTEGER," + // 3: user_id
                "\"OPERATOR_LIST\" TEXT," + // 4: operator_list
                "\"TASK_ID_LIST\" TEXT," + // 5: task_id_list
                "\"TIME\" INTEGER);"); // 6: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TIMING\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Timing entity) {
        stmt.clearBindings();
 
        Long timing_id = entity.getTiming_id();
        if (timing_id != null) {
            stmt.bindLong(1, timing_id);
        }
 
        String timing_name = entity.getTiming_name();
        if (timing_name != null) {
            stmt.bindString(2, timing_name);
        }
        stmt.bindLong(3, entity.getIs_on() ? 1L: 0L);
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(4, user_id);
        }
 
        List operator_list = entity.getOperator_list();
        if (operator_list != null) {
            stmt.bindString(5, operator_listConverter.convertToDatabaseValue(operator_list));
        }
 
        List task_id_list = entity.getTask_id_list();
        if (task_id_list != null) {
            stmt.bindString(6, task_id_listConverter.convertToDatabaseValue(task_id_list));
        }
 
        java.util.Date time = entity.getTime();
        if (time != null) {
            stmt.bindLong(7, time.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Timing entity) {
        stmt.clearBindings();
 
        Long timing_id = entity.getTiming_id();
        if (timing_id != null) {
            stmt.bindLong(1, timing_id);
        }
 
        String timing_name = entity.getTiming_name();
        if (timing_name != null) {
            stmt.bindString(2, timing_name);
        }
        stmt.bindLong(3, entity.getIs_on() ? 1L: 0L);
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(4, user_id);
        }
 
        List operator_list = entity.getOperator_list();
        if (operator_list != null) {
            stmt.bindString(5, operator_listConverter.convertToDatabaseValue(operator_list));
        }
 
        List task_id_list = entity.getTask_id_list();
        if (task_id_list != null) {
            stmt.bindString(6, task_id_listConverter.convertToDatabaseValue(task_id_list));
        }
 
        java.util.Date time = entity.getTime();
        if (time != null) {
            stmt.bindLong(7, time.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Timing readEntity(Cursor cursor, int offset) {
        Timing entity = new Timing( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // timing_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // timing_name
            cursor.getShort(offset + 2) != 0, // is_on
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // user_id
            cursor.isNull(offset + 4) ? null : operator_listConverter.convertToEntityProperty(cursor.getString(offset + 4)), // operator_list
            cursor.isNull(offset + 5) ? null : task_id_listConverter.convertToEntityProperty(cursor.getString(offset + 5)), // task_id_list
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Timing entity, int offset) {
        entity.setTiming_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTiming_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIs_on(cursor.getShort(offset + 2) != 0);
        entity.setUser_id(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setOperator_list(cursor.isNull(offset + 4) ? null : operator_listConverter.convertToEntityProperty(cursor.getString(offset + 4)));
        entity.setTask_id_list(cursor.isNull(offset + 5) ? null : task_id_listConverter.convertToEntityProperty(cursor.getString(offset + 5)));
        entity.setTime(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Timing entity, long rowId) {
        entity.setTiming_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Timing entity) {
        if(entity != null) {
            return entity.getTiming_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Timing entity) {
        return entity.getTiming_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

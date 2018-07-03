package edu.jiabao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * Created by lihui on 2017/12/17 0017.
 */

public class ClientPermission {
    public static void permissionManagement(Context context){
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(android.Manifest.permission.CAMERA,"照相机", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(android.Manifest.permission.READ_EXTERNAL_STORAGE, "读取手机内存", R.drawable.permission_ic_storage));
        HiPermission.create(context)
                .permissions(permissionItems)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {
                        //showToast(getString(R.string.permission_completed));
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                        //Log.i(TAG, "onDeny");
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                        //Log.i(TAG, "onGuarantee");
                    }
                });
    }
}

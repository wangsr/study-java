package org.wsr.stu.introspector.bean;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.Arrays;

/**
 * editor展示
 * Created by wangshengren on 16/10/12.
 */
public class PropertyEditorMain {
    public static void main(String[] args) {
        String[] path = PropertyEditorManager.getEditorSearchPath();
        System.out.println(Arrays.asList(path));
        PropertyEditor editor = PropertyEditorManager.findEditor(UserInfo.class);
        System.out.println(editor);
        //PropertyEditorManager.registerEditor(null, null);
        //PropertyEditorManager.setEditorSearchPath(null);
    }
}

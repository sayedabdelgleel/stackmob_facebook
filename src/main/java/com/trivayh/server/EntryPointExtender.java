package com.trivayh.server;

import com.stackmob.core.customcode.CustomCodeMethod;
import com.stackmob.core.jar.JarEntryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sayed
 * Date: 9/9/13
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class EntryPointExtender extends JarEntryObject {
    @Override
    public List<CustomCodeMethod> methods() {
        List<CustomCodeMethod> list = new ArrayList<CustomCodeMethod>();  //To change body of implemented methods use File | Settings | File Templates.
        list.add(new TestRequest());
        return list;
    }
}

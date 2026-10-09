package org.kenny.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener
{
    //监听添加属性
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("添加了属性");
    }
    //监听移除属性
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("移除了属性");
    }

    //监听属性改变
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("属性值被替换");
    }
}

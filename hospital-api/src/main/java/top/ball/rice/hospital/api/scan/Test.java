package top.ball.rice.hospital.api.scan;

import top.ball.rice.hospital.api.annotation.Generation;
import top.ball.rice.hospital.api.util.PackageUtils;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        PackageUtils util = new PackageUtils("top.ball.rice.hospital");
        List<Class<?>> classes = util.getClasses();
        classes.forEach(
                aClass -> {
                    Class<Generation> generation = Generation.class;//CheckString的class
                    if (aClass.isAnnotationPresent(generation)) {//判断是否被Generation注解所标识

                        System.out.println(aClass.getName());
                    }
//                    System.out.println(aClass.getName());
                });
    }
}

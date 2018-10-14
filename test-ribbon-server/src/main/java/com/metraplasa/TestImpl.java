package com.metraplasa;

public class TestImpl implements TestInterface {
    @Override
    public Test buildTest(String name, Integer age) {
        Test res = new Test();
        res.setName(name);
        res.setAge(age);
        System.out.println("doing build : " + res);
        return res;
    }

    @Override
    public Test doTest(String name, Integer age) {
        Test res = new Test();
        res.setName(name);
        res.setAge(age);
        System.out.println("doing test : " + res);
        return res;
    }
}

package com.github.paohaijiao.context;

import cn.hutool.core.convert.impl.CurrencyConverter;
import com.github.paohaijiao.fatory.JValueContextFactory;
import com.github.paohaijiao.obj.Address;
import com.github.paohaijiao.obj.Employee;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JContextTest {
    @Test
    public void string() throws IOException {
        String name = JValueContextFactory.fromJson("{\"name\":\"John\"}")
                .as(String.class)
                .query("$.name");
        System.out.println(name);
    }
    @Test
    public void map() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("date", "2023-01-01");
        LocalDate date = JValueContextFactory.fromMap(data)
                .as(LocalDate.class)
                .query("$.date");
        System.out.println(date);
    }
    @Test
    public void object() throws IOException {
        Employee person = new Employee("John", new Address("123 Main St"));
        String street = JValueContextFactory.fromObject(person)
                .as(String.class)
                .query("$.address.street");
//        System.out.println(date);
    }
    @Test
    public void object1() throws IOException {
        System.out.println();
//        List<LocalDate> dates = JValueContextFactory.fromJson(jsonData)
//                .as(new TypeReference<List<LocalDate>>() {})
//                .query(JSONPathQueryBuilder.query()
//                        .filter(/* ... */)
//                        .select(/* ... */)
//                        .build());
    }
    @Test
    public void object2() throws IOException {
        JValueContext.registerConverter(Currency.class, new CurrencyConverter());
        Currency currency = JValueContextFactory.fromJson("{\"price\":\"USD\"}")
                .as(Currency.class)
                .query("$.price");
    }
}

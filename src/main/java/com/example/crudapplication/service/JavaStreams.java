package com.example.crudapplication.service;

import com.example.crudapplication.entity.Employee;
import com.example.crudapplication.entity.Item;
import com.example.crudapplication.entity.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class JavaStreams {

    public static void main(String[] args) {

//        Stream<Integer> integerStream = Stream.of(1,2,3,3,4,5,6,7);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Stream<Integer> integerStream = list.stream();
        Integer[] evenIntegers = integerStream.filter(i -> i % 2 == 0).toArray(Integer[]::new);

        for (Integer evenInteger : evenIntegers) {
            System.out.println(evenInteger);
        }

        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

        memberNames.stream().filter(names -> names.startsWith("S")).sorted().map(String::toUpperCase).forEach(System.out::println);

        Predicate<String> filterNames = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (s.startsWith("S")) {
                    return true;
                }
                return false;
            }
        };

        System.out.println("inside custom predicate");
        memberNames.stream().filter(filterNames).sorted().map(String::toUpperCase).forEach(System.out::println);

        String concatName = String.valueOf(memberNames.stream().filter(names -> names.startsWith("S")).sorted().map(String::toUpperCase)
                .reduce((name1, name2) -> name1 + " " + name2));
        System.out.println(concatName);

        long count = memberNames.stream().filter(names -> names.startsWith("S")).sorted().map(String::toUpperCase).count();
        System.out.println(count);

        boolean matchResult = memberNames.stream().allMatch(names -> names.startsWith("S"));
        System.out.println(matchResult);

        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);
        List<Integer> list3 = Arrays.asList(7,8,9);

        List<List<Integer>> listList = Arrays.asList(list1,list2,list3);
        System.out.println(listList);

        List<Integer> integerList = listList.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        System.out.println(integerList);

        List<Integer> list4 = Arrays.asList(2, 4, 1, 3, 7, 5, 9, 6, 8);

        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            };
        };

        List<Integer> sortedList = list4.stream()
                .sorted(integerComparator)
                .collect(Collectors.toList());

        System.out.println(sortedList);

        /**
         * GROUP BY CLAUSE
         */
        List<Item> items = Arrays.asList(
                new Item("apple", 1, new BigDecimal("9.99"), new Person(1, "VC")),
                new Item("banana", 20, new BigDecimal("19.99"), new Person(2, "abc")),
                new Item("orang", 100, new BigDecimal("29.99"), new Person(2, "abc")),
                new Item("watermelon", 70, new BigDecimal("29.99"), new Person(4, "bnsjb")),
                new Item("papaya", 40, new BigDecimal("9.99"), new Person(6, "ndjd")),
                new Item("apple", 10000, new BigDecimal("9.99"), new Person(6, "ndjd")),
                new Item("banana", 10, new BigDecimal("19.99"), new Person(4, "bnsjb")),
                new Item("apple", 2, new BigDecimal("9.99"), new Person(4, "bnsjb"))
        );

        Map<Person, Optional<Item>> groupByPriceMap =
                items.stream().collect(Collectors.groupingBy(Item::getPerson, maxBy(Comparator.comparing(Item::getQty))));
        System.out.println(groupByPriceMap.values());

        Map<Integer, Person> usersMap = new HashMap<>();

        usersMap.put(1, new Person(1, "VC"));
        usersMap.put(1, new Person(1, "VC"));
        usersMap.put(2, new Person(3, "CC"));
        usersMap.put(3, new Person(1, "VC"));
        usersMap.put(3, new Person(1, "VC"));
        usersMap.put(3, new Person(2, "AC"));
        usersMap.put(5, new Person(3, "CC"));

        System.out.println("usersMap" + usersMap);

        List<Integer> idList = Arrays.asList(1,3,5,7);

        Map<Integer, Person> integerPersonMap = usersMap.entrySet()
                .stream()
                .map(x -> {
                    x.getValue().setPersonId(x.getValue().getPersonId() + 2);
                    return x;
                })
                .filter(x -> idList.contains(x.getValue().getPersonId()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(integerPersonMap);

        List<Person> integerPersonMap1 = idList.stream()
                .map(usersMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        System.out.println(integerPersonMap1);

        List<Item> items1 = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99"), new Person(1, "VC")),
                new Item("banana", 20, new BigDecimal("19.99"), new Person(2, "abc")),
                new Item("orang", 10, new BigDecimal("29.99"), new Person(2, "abc")),
                new Item("watermelon", 70, new BigDecimal("29.99"), new Person(4, "bnsjb")),
                new Item("papaya", 20, new BigDecimal("9.99"), new Person(6, "ndjd")),
                new Item("apple", 10, new BigDecimal("9.99"), new Person(6, "ndjd")),
                new Item("banana", 10, new BigDecimal("19.99"), new Person(4, "bnsjb")),
                new Item("apple", 20, new BigDecimal("9.99"), new Person(4, "bnsjb"))
        );

        // 1 - Using default equals() methods

        // Get distinct people by id
        List<Item> distinctElements = items1.stream()
                .filter(distinctByCustomFields(Item::getPrice))
                .collect(Collectors.toList());
        System.out.println("distinctElements = " + distinctElements);

        Stream<Integer> s1 = Stream.concat(Stream.of(5,6,7), Stream.of(1,2,3,4));
        s1.forEach(System.out::println);

        // get last element
        List<Integer> stream = Arrays.asList(1,2,3,45,45,56,3,1);
        Integer lastElement = stream.stream().reduce((s3,s2) -> s2).orElseThrow(() -> new RuntimeException("no last element"));
        System.out.println("last element : " + lastElement);

        // next 3 days
        List<LocalDate> localDates = Stream.iterate(LocalDate.now(), d -> d.plusDays(1))
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("localDates = " + localDates);

        Predicate<Integer> action = e -> e%2 == 0;
        List<Integer> even = stream.stream().filter(action).collect(Collectors.toList());
        System.out.println("even = " + even);

        // count duplicates
        Map<Integer, Integer> countDuplicates = stream.stream()
                .collect(Collectors.toMap(Function.identity(), x -> 1, Integer::sum));
        System.out.println("countDuplicates = " + countDuplicates);

        Stream<String> characters = Stream.of("a","b","c","d","e","f","g");
        //characters.sorted().forEach(System.out::println);
        characters.sorted(Comparator.reverseOrder()).forEach(System.out::println);

        String[] strArray = { "Alex", "Charles", "Dean", "Amanda", "Brian" };

        strArray = Arrays.stream(strArray).sorted().toArray(String[]::new);
        System.out.println("strArray = " + Arrays.toString(strArray));

        strArray = Arrays.stream(strArray).sorted(Comparator.reverseOrder()).toArray(String[]::new);
        System.out.println("strArray = " + Arrays.toString(strArray));

        // sorting hashmap
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("alex", 1);
        unsortMap.put("david", 2);
        unsortMap.put("elle", 3);
        unsortMap.put("charles", 4);
        unsortMap.put("brian", 5);

        // sorting using java8 streams

        LinkedHashMap<String, Integer> orderMap = new LinkedHashMap<>();
        unsortMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> orderMap.put(x.getKey(), x.getValue()));
        System.out.println("orderMap = " + orderMap);
        orderMap.clear();
        unsortMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> orderMap.put(x.getKey(), x.getValue()));
        System.out.println("orderMap = " + orderMap);

        // sorting using tree map
        Map<String, Integer> treeMap = new TreeMap<>(unsortMap);
        System.out.println("treeMap = " + treeMap);

        Map<String, Integer> treeMap1 = new TreeMap<>(Collections.reverseOrder());
        treeMap1.putAll(unsortMap);
        System.out.println("treeMap1 = " + treeMap1);

        // sorting using custom fields
        Comparator<Item> compareByQty = Comparator.comparing(Item::getQty);
        List<Item> sortByQty = items1.stream().sorted(compareByQty).collect(toList());
        System.out.println("sortByQty = " + sortByQty);

        List<Employee> employeesList = Arrays.asList(
                new Employee(1, "Alex", 100),
                new Employee(2, "Brian", 200),
                new Employee(3, "Charles", 300),
                new Employee(4, "David", 400),
                new Employee(5, "Edward", 500),
                new Employee(6, "Frank", 600)
        );

        Predicate<Employee> idLessThan4 = x -> x.getEmpId()<4;
        Predicate<Employee> salaryGreaterThan200 = x -> x.getEmpSalary()>200;

        List<Employee> filteredEmployess = employeesList.stream().filter(idLessThan4.and(salaryGreaterThan200)).collect(toList());
        System.out.println("filteredEmployess = " + filteredEmployess);
        List<Employee> filteredEmployess1 = employeesList.stream().filter(idLessThan4.or(salaryGreaterThan200)).collect(toList());
        System.out.println("filteredEmployess1 = " + filteredEmployess1);

    }

    private static <T> Predicate<T> distinctByKeys(final Function<? super T, ?>... keyExtractors)
    {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();
        return t ->
        {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> ke.apply(t))
                    .collect(Collectors.toList());
            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }

    public static <T> Predicate<T> distinctByCustomFields(Function<T, Object> function) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(function.apply(t));
    }

}
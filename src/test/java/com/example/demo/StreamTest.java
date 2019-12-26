package com.example.demo;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args){
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
        IntSummaryStatistics iss = random.ints().limit(10).summaryStatistics();
        System.out.println(iss.getMax());
        System.out.println(iss.getAverage());
        System.out.println(iss.getMin());
        System.out.println(iss.getSum());
        System.out.println(iss.toString());
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        long count = strings.parallelStream().filter(string -> !string.isEmpty()).count();
        System.out.println(count);
        List<String> newStrings = strings.parallelStream().map(str -> str+"new" ).sorted((x,y) -> x.compareTo(y)).collect(Collectors.toList());
        System.out.println(newStrings);
        ArrayList<Employee> employees = new ArrayList<>();
        String[] prefix = {"B", "A"};
        for (int i = 1; i <= 10; i++)
            employees.add(new Employee(prefix[i % 2] + i, i * 1000));
        employees.forEach(System.out::println);
        System.out.println("公司进行薪资调整...");
        salaryAdjustment(employees,new SalaryAdjustment(4000));
        employees.forEach(System.out::println);

        //collectors

        Student studentA = new Student("20190001","小明");
        Student studentB = new Student("20190002","小红");
        Student studentC = new Student("20190003","小丁");
        Student studentD = new Student("20190004","小冰");


        //Function.identity() 获取这个对象本身，那么结果就是Map<String,Student> 即 id->student
        //串行收集
        Map<String,Student> stuMap = Stream.of(studentA,studentB,studentC,studentD)
                .collect(Collectors.toMap(Student::getId, Function.identity(), BinaryOperator
                        .maxBy(Comparator.comparing(Student::getName))));

        //并发收集
        Map<String,Student> cstuMap = Stream.of(studentA,studentB,studentC,studentD)
                .parallel()
                .collect(Collectors.toConcurrentMap(Student::getId,Function.identity()));

        //================================================================================

        //Map<String,String> 即 id->name
        //串行收集
        Map<String,String> stuMap1 = Stream.of(studentA,studentB,studentC,studentD)
                .collect(Collectors.toMap(Student::getId,Student::getName));

        //并发收集
        Map<String,String> cstuMap1 = Stream.of(studentA,studentB,studentC,studentD)
                .parallel()
                .collect(Collectors.toConcurrentMap(Student::getId,Student::getName));
        Map<String,String> cstuMap2 = Stream.of(studentA,studentB,studentC)
                .collect(Collectors.toConcurrentMap(Student::getId,Student::getName));
        System.out.println("==============");

        IntSummaryStatistics intss = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.summarizingInt(Integer::valueOf));
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).mapToInt(Integer::valueOf)
                .sum();
        Optional<Integer> sumStudent = Stream.of(studentA, studentB, studentC,studentD)
                .map(student -> student.getName().length())
                .collect(Collectors.reducing(Integer::sum));
        Integer sumStudent1 = Stream.of(studentA, studentB, studentC)
                .collect(Collectors.reducing(0, s -> ((Student) s).getName().length(), Integer::sum));
        Map<String,List<Integer>> map = Stream.of(-6, -7, -8, -9, 1, 2, 3, 4, 5, 6, 0)
                .collect(Collectors.groupingBy(integer -> {
                    if (integer < 0) {
                        return "小于";
                    } else if (integer == 0) {
                        return "等于";
                    } else {
                        return "大于";
                    }
                }));
        List<String> names = Stream.of(studentA,studentB,studentC,studentD)
                .collect(Collectors.mapping(Student::getName,Collectors.toList()));
        System.out.println("==============");
    }

    private static void salaryAdjustment(List<Employee> employees,UnaryOperator<Employee> uo){
        for(int i=0;i<employees.size();i++){
            employees.set(i,uo.apply(employees.get(i)));
        }
    }

    static class SalaryAdjustment implements UnaryOperator<Employee>{

        private int salary;
        public SalaryAdjustment(int salary){
            this.salary = salary;
        }
        @Override
        public Employee apply(Employee employee) {
            employee.setSalary(this.salary);
            return employee;
        }
    }
    static class Employee{
        private String name;
        private int salary;

        public Employee() {
            this.salary = 4000;
        }

        public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("name:").append(name)
                    .append(",salary:").append(salary)
                    .toString();
        }
    }
}

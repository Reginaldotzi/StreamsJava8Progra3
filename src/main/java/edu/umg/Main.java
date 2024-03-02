package edu.umg;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Crear una lista de empleados
        List<Employee> empList = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000.0),
                new Employee(2, "Bill Gates", 200000.0),
                new Employee(3, "Mark Zuckerberg", 300000.0),
                new Employee(4, "Elon Musk", 400000.0),
                new Employee(5, "Warren Buffett", 500000.0));

        // forEach
        System.out.println("forEach:");
        empList.forEach(System.out::println);

        // map
        System.out.println("\nmap:");
        empList.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        // collect
        System.out.println("\ncollect:");
        List<String> names = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(names);

        // filter
        System.out.println("\nfilter:");
        List<Employee> highSalaryEmployees = empList.stream()
                .filter(e -> e.getSalary() > 300000)
                .collect(Collectors.toList());
        System.out.println(highSalaryEmployees);

        // findFirst
        System.out.println("\nfindFirst:");
        Employee firstEmployee = empList.stream()
                .findFirst()
                .orElse(null);
        System.out.println(firstEmployee);

        // toArray
        System.out.println("\ntoArray:");
        Employee[] employeeArray = empList.stream()
                .toArray(Employee[]::new);
        System.out.println(Arrays.toString(employeeArray));

        // flatMap
        System.out.println("\nflatMap:");
        List<List<Character>> charList = Arrays.asList(
                Arrays.asList('a', 'b'),
                Arrays.asList('c', 'd'),
                Arrays.asList('e', 'f'));
        List<Character> flatList = charList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(flatList);

        // peek
        System.out.println("\npeek:");
        empList.stream()
                .peek(e -> e.salaryIncrement(5000.0))
                .forEach(System.out::println);


        // Ejemplo de un stream pipeline
        System.out.println("Stream Pipeline:");
        List<String> result = names.stream()
                .filter(name -> name.startsWith("J"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(result);


        // Ejemplo de operación lazy
        System.out.println("\nLazy Operation:");
        Stream<String> lazyStream = names.stream()
                .filter(name -> {
                    System.out.println("Filtering: " + name);
                    return name.startsWith("J");
                });

        System.out.println("Stream creado, pero no procesado aún");

        List<String> filteredNames = lazyStream.collect(Collectors.toList());
        System.out.println(filteredNames);

        //Operaciones Streams Basdas en comparación.
        // sorted
        System.out.println("\nsorted:");
        empList.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(System.out::println);

        // min
        System.out.println("\nmin:");
        Employee minSalaryEmployee = empList.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
        System.out.println(minSalaryEmployee);

        // max
        System.out.println("\nmax:");
        Employee maxSalaryEmployee = empList.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
        System.out.println(maxSalaryEmployee);

        // distinct
        System.out.println("\ndistinct:");
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctNumbers);

        // allMatch
        System.out.println("\nallMatch:");
        boolean allSalariesAbove100000 = empList.stream()
                .allMatch(e -> e.getSalary() > 100000);
        System.out.println(allSalariesAbove100000);

        // anyMatch
        System.out.println("\nanyMatch:");
        boolean anySalariesAbove400000 = empList.stream()
                .anyMatch(e -> e.getSalary() > 400000);
        System.out.println(anySalariesAbove400000);

        // noneMatch
        System.out.println("\nnoneMatch:");
        boolean noSalariesAbove600000 = empList.stream()
                .noneMatch(e -> e.getSalary() > 600000);
        System.out.println(noSalariesAbove600000);
        //Operaciones especializadas
        // sum
        System.out.println("\nsum:");
        double totalSalary = empList.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println(totalSalary);

        // average
        System.out.println("\naverage:");
        double averageSalary = empList.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println(averageSalary);

        // range
        System.out.println("\nrange:");
        IntStream.range(1, 10)
                .forEach(System.out::println);
        //Operaciones de reducción
        // reduce
        System.out.println("\nreduce:");
        double totalSalaryReduce = empList.stream()
                .mapToDouble(Employee::getSalary)
                .reduce(0, Double::sum);
        System.out.println(totalSalaryReduce);
        //Advanced collet
        // joining
        System.out.println("\njoining:");
        String allNames = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println(allNames);

        // toSet
        System.out.println("\ntoSet:");
        Set<String> nameSet = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        System.out.println(nameSet);

        // toCollection
        System.out.println("\ntoCollection:");
        LinkedList<String> nameLinkedList = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(nameLinkedList);

        // summarizingDouble
        System.out.println("\nsummarizingDouble:");
        DoubleSummaryStatistics stats = empList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(stats);

        // partitioningBy
        System.out.println("\npartitioningBy:");
        Map<Boolean, List<Employee>> partitioned = empList.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 300000));
        System.out.println(partitioned);

        // groupingBy
        System.out.println("\ngroupingBy:");
        Map<Character, List<Employee>> groupedByFirstLetter = empList.stream()
                .collect(Collectors.groupingBy(e -> e.getName().charAt(0)));
        System.out.println(groupedByFirstLetter);

        // mapping
        System.out.println("\nmapping:");
        Map<Character, List<String>> mappedByFirstLetter = empList.stream()
                .collect(Collectors.groupingBy(e -> e.getName().charAt(0),
                        Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(mappedByFirstLetter);

        // reducing
        System.out.println("\nreducing:");
        Double totalSalaryReducing = empList.stream()
                .collect(Collectors.reducing(0.0, Employee::getSalary, Double::sum));
        System.out.println(totalSalaryReducing);

        // Parallel Streams
        System.out.println("\nParallel Streams:");
        empList.parallelStream()
                .forEach(System.out::println);

        //Infinite Streams
        // generate
        System.out.println("\ngenerate:");
        Stream.generate(() -> "Java")
                .limit(3)
                .forEach(System.out::println);


        //Iterate
        System.out.println("iterate:");
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::println);



    }
}
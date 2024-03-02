package edu.umg;

public class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void salaryIncrement(double incrementAmount) {
        this.salary += incrementAmount;
    }

    public static Employee findById(int id) {
        // Aquí deberías implementar la lógica para buscar un empleado por su id
        // y retornar una instancia de Employee si se encuentra, de lo contrario null.
        return null; // Este es un ejemplo, deberías implementar la lógica real aquí.
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

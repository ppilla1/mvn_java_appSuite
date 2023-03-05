package io.explore.functional;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@FunctionalInterface
interface Builder<T> {
    void register(String label, Supplier<T> factory);
}
@ToString
class Shape {
    String name = "Shape";
}

@ToString
class Circle extends Shape{
}

@ToString
class Rectangle extends Shape{
    public void Rectangle(){
        super.name = "Rectangle";
    }
}

@ToString
class Triangle extends Shape{
    public void Triangle(){
        super.name = "Triangle";
    }
}

@FunctionalInterface
interface Registry<T> {
    Supplier<? extends T> buildFactory(String label);

    static <T> Registry<T> createRegistry(Consumer<Builder<T>> builderConsumer){
        Map<String, Supplier<T>> factoryMap = new HashMap<>();
        Builder<T> builder = (label, factory) -> factoryMap.put(label, factory);
        builderConsumer.accept(builder);
        System.out.println(factoryMap);
        return (label) -> factoryMap.computeIfAbsent(label, s -> {throw new IllegalArgumentException(s + " Factory ");});
    }
}
@Log4j2
public class BuilderRegistryTest {

    @Test
    public void create_all_shape_builder_registry(){
        List<String> levelsProcessed = new ArrayList<>();
        Consumer<String> stage1 = s -> {
            String alert = String.format("%s are at level-1", s);
            levelsProcessed.add(alert);
            log.info("{}\n{}", alert, levelsProcessed);
        };

        Consumer<String> stage2 = s -> {
            String alert = String.format("%s are at level-2", s);
            levelsProcessed.add(alert);
            log.info("{}\n{}", alert, levelsProcessed);
        };

        Consumer<String> stage3 = s -> {
            String alert = String.format("%s are at level-3", s);
            levelsProcessed.add(alert);
            log.info("{}\n{}", alert, levelsProcessed);
        };

        Consumer<String> consumer = stage1.andThen(stage2).andThen(stage3);

        consumer.accept("Iam");
    }
}

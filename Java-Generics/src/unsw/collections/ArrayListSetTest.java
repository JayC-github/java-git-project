package unsw.collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import unsw.fruit.Apple;
import unsw.fruit.Fruit;
import unsw.fruit.Orange;

class ArrayListSetTest {

    @Test
    void testBasics() {
        Set<String> set = new ArrayListSet<>();
        set.add("Hello");
        set.add("World");
        assertTrue(set.contains("Hello"));
        assertTrue(set.contains("World"));

        set.remove("Hello");
        assertFalse(set.contains("Hello"));
        assertTrue(set.contains("World"));
    }

    @Test
    void testSubsetOf() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Apple> apples = new ArrayListSet<>();
        apples.add(new Apple("Gala"));
        apples.add(new Apple("Fuji"));

        assertTrue(apples.subsetOf(fruit));
        assertFalse(fruit.subsetOf(apples));

        fruit.remove(new Apple("Fuji"));

        assertFalse(apples.subsetOf(fruit));
    }

    @Test
    void testUnion() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Orange> orange = new ArrayListSet<>();
        orange.add(new Orange("JayOrange"));
        orange.add(new Orange("MercyOrange"));
        //orange.add(new Orange("Navel"));

        Set<Fruit> union = fruit.union(orange);

        assertTrue(union.size() == 5);

        for(Fruit f: fruit) {
            assertTrue(union.contains(f));
        }

        for (Orange o: orange) {
            assertTrue(union.contains(o));
        }

        orange.add(new Orange("Navel"));
        fruit.union(orange);

        assertTrue(union.size() == 5);

    }

    @Test
    void testIntersection() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Orange> orange = new ArrayListSet<>();
        orange.add(new Orange("JayOrange"));
        orange.add(new Orange("MercyOrange"));

        Set<Fruit> inter = fruit.intersection(orange);
        assertTrue(inter.size() == 0);

        orange.add(new Orange("Navel"));
        fruit.add(new Orange("MercyOrange"));
        inter = fruit.intersection(orange);
        
        assertTrue(inter.size() == 2);
        for (Fruit f: inter) {
            assertTrue(fruit.contains(f) && orange.contains(f));
        }

    }

    @Test
    void testEqual() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Orange> orange = new ArrayListSet<>();
        orange.add(new Orange("JayOrange"));
        orange.add(new Orange("MercyOrange"));
        orange.add(new Orange("Navel"));

        assertFalse(fruit.equals(orange));
        assertFalse(orange.equals(fruit));

        Set<Fruit> newfruit = new ArrayListSet<Fruit>();
        newfruit.add(new Apple("Gala"));
        newfruit.add(new Apple("Fuji"));
        newfruit.add(new Orange("Navel"));

        assertTrue(fruit.equals(fruit));
        assertTrue(newfruit.equals(newfruit));
        assertTrue(fruit.equals(newfruit));
        assertTrue(newfruit.equals(fruit));

        Set<Apple> newApple = new ArrayListSet<Apple>();
        Set<Orange> newOrange = new ArrayListSet<Orange>();
        assertTrue(newApple.equals(newOrange));

        newOrange.add(new Orange("BabyMercy"));
        assertFalse(newApple.equals(newOrange));

        ArrayListSet<Fruit> newJay = new ArrayListSet<Fruit>();
        newJay.add(new Apple("Gala"));
        newJay.add(new Apple("Fuji"));
        newJay.add(new Orange("Navel"));
        
        LinkedList<Fruit> newMercy = new LinkedList<Fruit>();
        newMercy.add(new Apple("Gala"));
        newMercy.add(new Apple("Fuji"));
        newMercy.add(new Orange("Navel"));
        
        assertTrue(newJay.equals(newMercy));

    }

}

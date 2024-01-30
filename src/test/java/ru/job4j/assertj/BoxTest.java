package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void testVerticesSphere() {
        Box sphere = new Box(0, 5);
        assertThat(sphere.whatsThis()).isEqualTo("Sphere");
        assertThat(sphere.getNumberOfVertices()).isEqualTo(0);
        assertThat(sphere.isExist()).isTrue();
    }

    @Test
    void testVerticesTetrahedron() {
        Box tetrahedron = new Box(4, 5);
        assertThat(tetrahedron.whatsThis()).isEqualTo("Tetrahedron");
        assertThat(tetrahedron.getNumberOfVertices()).isEqualTo(4);
        assertThat(tetrahedron.isExist()).isTrue();
    }

    @Test
    void testVerticesCube() {
        Box cube = new Box(8, 5);
        assertThat(cube.whatsThis()).isEqualTo("Cube");
        assertThat(cube.getNumberOfVertices()).isEqualTo(8);
        assertThat(cube.isExist()).isTrue();
    }

    @Test
    void testVerticesUnknownObject() {
        Box unknownObject = new Box(2, 22);
        assertThat(unknownObject.whatsThis()).isEqualTo("Unknown object");
        assertThat(unknownObject.getNumberOfVertices()).isEqualTo(-1);
        assertThat(unknownObject.isExist()).isFalse();
    }

    @Test
    void testGetAreaSphere() {
        Box sphere = new Box(0, 5);
        assertThat(sphere.getArea()).isEqualTo(4 * Math.PI * (5 * 5), within(0.0001));
    }

    @Test
    void testGetAreaTetrahedron() {
        Box tetrahedron = new Box(4, 5);
        assertThat(tetrahedron.getArea()).isEqualTo(Math.sqrt(3) * (5 * 5), within(0.0001));
    }

    @Test
    void testGetAreaCube() {
        Box cube = new Box(8, 5);
        assertThat(cube.getArea()).isEqualTo(6 * (5 * 5), within(0.0001));
    }

    @Test
    void testGetAreaUnknownBox() {
        Box unknownBox = new Box(2, 11);
        assertThat(unknownBox.getArea()).isEqualTo(0);
    }
}
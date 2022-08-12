package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(5, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .contains("object")
                .startsWith("Unknown");
    }

    @Test
    void checkStringChain() {
        Box box = new Box(4, 12);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(4)
                .isNotZero()
                .isPositive()
                .isEven();
    }

    @Test
    void checkStringChainSecond() {
        Box box = new Box(6, 12);
        String result = String.valueOf(box.getNumberOfVertices());
        assertThat(result).isEqualTo(String.valueOf(6))
                .doesNotContain("5", "0", "-1")
                .isNotEmpty();

    }

    @Test
    void checkIsExist() {
        Box box = new Box(6, 12);
        boolean res = box.isExist();
        assertThat(res).isTrue()
                .isNotNull();
    }

    @Test
    void checkIsNotExist() {
        Box box = new Box(5, 12);
        boolean res = box.isExist();
        assertThat(res).isEqualTo(false)
                .isFalse()
                .isNotNull();
    }

    @Test
    void checkGetAreaSix() {
        Box box = new Box(6, 2);
        double res = box.getArea();
        assertThat(res)
                .isCloseTo(24, Percentage.withPercentage(0.01))
                .isEqualTo(24)
                .isCloseTo(24, withPrecision(0.01d));
    }

    @Test
    void checkGetAreaDefault() {
        Box box = new Box(5, 2);
        double res = box.getArea();
        assertThat(res).isEqualTo(0)
                .isNotNull()
                .isLessThan(1);
    }
}
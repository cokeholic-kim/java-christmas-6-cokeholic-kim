package christmas.Model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    @DisplayName("뱃지의 리턴값 테스트")
    void returnBadge() {
        int price = 31246;
        assertThat(
                Badge.returnBadge(price)
        ).isEqualTo(Badge.SANTA);
    }
}
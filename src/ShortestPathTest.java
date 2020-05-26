import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShortestPathTest {

	@Test
	void test() {
		ShortestPath guateRoad = new ShortestPath();
		assertEquals("[quiche, totonicapan, guatemala, huehuetenango, escuintla, chimaltenango, sacatepequez]", guateRoad.cityNames.toString());
	}

}

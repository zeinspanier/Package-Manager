import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

class PackageManagerTest {
	
	PackageManager test = new PackageManager();

	@Test
	void test000_add() {
		
		try {
			test.constructGraph("cyclic.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
		
	}
	
	@Test
	void test001_remove() {
		boolean[] visited = new boolean[2];
		System.out.print(visited[0]);
		System.out.print(visited[1]);
		//fail("Not yet implemented");
	}
	
	@Test
	void test002_add() {
		fail("Not yet implemented");
	}

}

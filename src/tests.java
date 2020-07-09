import static org.junit.Assert.*;

@Test
public void meetingsOverlapTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(2, 4));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 4));
    assertEquals(expected, actual);
}
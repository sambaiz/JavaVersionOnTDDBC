import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;


public class VesionTest {

	@Test
	public void バージョン名がＪＤＫxuではじまってuの後が正の整数であること() {
		assertFalse(Version.isValid("JDK7uXX"));
		assertTrue(Version.isValid("JDK7u0"));
		assertFalse(Version.isValid("hogehogeJDK7u0"));
		assertFalse(Version.isValid("JDK7uhogehoge"));
		assertFalse(Version.isValid("JDK7u7x"));
		assertFalse(Version.isValid(null));
	}

	@Test(expected=ParseException.class)
	public void 有効ではないバージョンに対して例外を投げる() throws ParseException {
		Version.parse("JDKxuy");
	}

	@Test
	public void JDKxuyに対してfamilyNumberがx_updateNumberがyなオブジェクトを返す() throws ParseException{
		assertEquals(70, Version.parse("JDK70u10").familyNumber);
		assertEquals(10, Version.parse("JDK70u10").updateNumber);
		assertFalse(Version.isValid(null));
	}

	@Test
	public void 自身のfamilyNumberが引数のfamilyNumberより小さかった場合True() throws ParseException {
		assertTrue(Version.parse("JDK7u0").lt(Version.parse("JDK8u0")));
		assertFalse(Version.parse("JDK8u0").lt(Version.parse("JDK7u0")));
	}

	@Test
	public void familyNumberが等しく自身のupdateNumberが引数のupdateNumberより小さい場合true() throws ParseException {
		assertTrue(Version.parse("JDK7u0").lt(Version.parse("JDK7u1")));
		assertFalse(Version.parse("JDK7u0").lt(Version.parse("JDK7u0")));
	}

	@Test
	public void 自身のfamilyNumberが引数のfamilyNumberより大きかった場合True() throws ParseException {
		assertFalse(Version.parse("JDK7u0").gt(Version.parse("JDK8u0")));
		assertTrue(Version.parse("JDK8u0").gt(Version.parse("JDK7u0")));
	}

	@Test
	public void familyNumberが等しく自身のupdateNumberが引数のupdateNumberより大きい場合true() throws ParseException {
		assertTrue(Version.parse("JDK7u1").gt(Version.parse("JDK7u0")));
		assertFalse(Version.parse("JDK7u0").gt(Version.parse("JDK7u0")));
	}

	@Test
	public void 次のNextLimitedUpdateを返す() throws ParseException {
		assertEquals(20, Version.parse("JDK7u1").nextLimitedUpdate().updateNumber);
		assertEquals(40, Version.parse("JDK7u21").nextLimitedUpdate().updateNumber);
		assertEquals(40, Version.parse("JDK7u20").nextLimitedUpdate().updateNumber);
		assertEquals(7, Version.parse("JDK7u1").nextLimitedUpdate().familyNumber);
	}
	
	@Test
	public void 次のCliticalPatchUpdateを返す() throws ParseException{
		assertEquals(5, Version.parse("JDK7u1").nextCliticalPatchUpdate().updateNumber);
		assertEquals(11, Version.parse("JDK7u5").nextCliticalPatchUpdate().updateNumber);
	}
	
	@Test
	public void 次のSecurityAlertを返す() throws ParseException{
		assertEquals(2, Version.parse("JDK7u1").nextSecurityAlert().updateNumber);
		assertEquals(3, Version.parse("JDK7u2").nextSecurityAlert().updateNumber);
		assertEquals(6, Version.parse("JDK7u4").nextSecurityAlert().updateNumber);
		/*
		assertEquals(10, Version.parse("JDK7u9").nextSecurityAlert().updateNumber);
		assertEquals(22, Version.parse("JDK7u19").nextSecurityAlert().updateNumber);
		*/
	}
}
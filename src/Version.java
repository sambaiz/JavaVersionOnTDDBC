import java.text.ParseException;
import java.util.regex.Pattern;


public class Version {
	private static final String VERSION_STRING = "^JDK[1-9]?[0-9]+u[1-9]?[0-9]+$";
	private static final Pattern VERSION_PATTERN = Pattern.compile(VERSION_STRING);

	public static boolean isValid(String version) {
		;
		return version != null && VERSION_PATTERN.matcher(version).find();
	}

	public static VersionNumber parse(String version) throws ParseException {
		if(!isValid(version)) throw new ParseException(version, 0);
		int updateNumber = Integer.parseInt(version.split("u")[1]);
		int familyNumber = Integer.parseInt(version.split("u")[0].substring("JDK".length()));
		return new VersionNumber(familyNumber,updateNumber);
	}

}

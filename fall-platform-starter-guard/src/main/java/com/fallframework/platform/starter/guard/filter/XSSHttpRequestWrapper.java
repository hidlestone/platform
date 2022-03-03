package com.fallframework.platform.starter.guard.filter;

/**
 * @author payn
 */
public class XSSHttpRequestWrapper /*extends BodyReaderHttpServletRequestWrapper*/ {

//	public static final LookupTranslator APOS_ESCAPE_TRANSLATOR;
//	public static final Map<CharSequence, CharSequence> BRACKETS;
//	public static LookupTranslator BRACKETS_ESCAPE_TRANSLATOR;
//	public static final Map<CharSequence, CharSequence> LINEBREAK;
//	public static LookupTranslator LINEBREAK_ESCAPE_TRANSLATOR;
//	public static final Map<CharSequence, CharSequence> CUSTOM;
//	public static LookupTranslator CUSTOM_ESCAPE_TRANSLATOR;
//	public static final Map<CharSequence, CharSequence> CUSTOM_REVERSE;
//	public static LookupTranslator CUSTOM_UNESCAPE_TRANSLATOR;
//	public static final List<String> VALIDATE_CHARACTERS;
//	public static CharSequenceTranslator ESCAPE_HTML4;
//	public static CharSequenceTranslator UNESCAPE_HTML4;
//
//	public XSSHttpRequestWrapper(HttpServletRequest request) {
//		super(request);
//	}
//
//	public Map<String, String[]> getParameterMap() {
//		Map<String, String[]> params = super.getParameterMap();
//		HashMap<String, String[]> copy = new HashMap();
//		Set<String> names = params.keySet();
//		Iterator var4 = names.iterator();
//
//		while (var4.hasNext()) {
//			String name = (String) var4.next();
//			Iterator var6 = VALIDATE_CHARACTERS.iterator();
//
//			while (var6.hasNext()) {
//				String character = (String) var6.next();
//				if (name.contains(character)) {
//					throw new IllegalArgumentException("Illegal parameter name [\"" + name + "\"].");
//				}
//			}
//
//			String[] values = (String[]) params.get(name);
//			String[] cvals = new String[values.length];
//			int i = 0;
//
//			for (int max = values.length; i < max; ++i) {
//				cvals[i] = this.escape(values[i]);
//			}
//
//			copy.put(this.escape(name), cvals);
//		}
//
//		return copy;
//	}
//
//	public String[] getParameterValues(String parameter) {
//		Iterator var2 = VALIDATE_CHARACTERS.iterator();
//
//		while (var2.hasNext()) {
//			String character = (String) var2.next();
//			if (parameter.contains(character)) {
//				throw new IllegalArgumentException("Illegal parameter name [\"" + parameter + "\"].");
//			}
//		}
//
//		String[] values = super.getParameterValues(parameter);
//		if (values == null) {
//			return null;
//		} else {
//			int count = values.length;
//			String[] encodedValues = new String[count];
//
//			for (int i = 0; i < count; ++i) {
//				encodedValues[i] = this.escape(values[i]);
//			}
//
//			return encodedValues;
//		}
//	}
//
//	public String getParameter(String parameter) {
//		Iterator var2 = VALIDATE_CHARACTERS.iterator();
//
//		String character;
//		do {
//			if (!var2.hasNext()) {
//				String value = super.getParameter(parameter);
//				return this.escape(value);
//			}
//
//			character = (String) var2.next();
//		} while (!parameter.contains(character));
//
//		throw new IllegalArgumentException("Illegal parameter name [\"" + parameter + "\"].");
//	}
//
//	public String getHeader(String name) {
//		Iterator var2 = VALIDATE_CHARACTERS.iterator();
//
//		String character;
//		do {
//			if (!var2.hasNext()) {
//				String value = super.getHeader(name);
//				return this.escape(value);
//			}
//
//			character = (String) var2.next();
//		} while (!name.contains(character));
//
//		throw new IllegalArgumentException("Illegal HEADER name [\"" + name + "\"].");
//	}
//
//	public String getBodyString(HttpServletRequest request, String charsetString) {
//		String bodyStr = super.getBodyString(request, charsetString);
//		bodyStr = this.escape(bodyStr);
//		return bodyStr;
//	}
//
//	protected String escape(String val) {
//		return val != null ? val.replaceAll("<\\s*script\\s*>", "").replaceAll("<\\s*/\\s*script\\s*>", "").replaceAll("javascript\\s*:", "").replaceAll("\\s*onerror\\s*=[\\s\\S]*\\s+", " ") : null;
//	}
//
//	static {
//		APOS_ESCAPE_TRANSLATOR = new LookupTranslator(EntityArrays.APOS_ESCAPE);
//		BRACKETS = new HashMap();
//		BRACKETS_ESCAPE_TRANSLATOR = null;
//		LINEBREAK = new HashMap();
//		LINEBREAK_ESCAPE_TRANSLATOR = null;
//		CUSTOM = new HashMap();
//		CUSTOM_ESCAPE_TRANSLATOR = null;
//		CUSTOM_REVERSE = new HashMap();
//		CUSTOM_UNESCAPE_TRANSLATOR = null;
//		VALIDATE_CHARACTERS = Arrays.asList("&", ";", "<", ">", "|", "%", "@", "$", "'", "\"", "\\", "(", ")", ",", "+");
//		ESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[0]);
//		UNESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[0]);
//		BRACKETS.put("(", "&#40;");
//		BRACKETS.put(")", "&#41;");
//		BRACKETS_ESCAPE_TRANSLATOR = new LookupTranslator(BRACKETS);
//		LINEBREAK.put("%0d%0a", "");
//		LINEBREAK.put("%0D%0A", "");
//		LINEBREAK_ESCAPE_TRANSLATOR = new LookupTranslator(LINEBREAK);
//		CUSTOM.put("%26", "&amp;");
//		CUSTOM.put("&", "&amp;");
//		CUSTOM.put("<", "&lt;");
//		CUSTOM.put(">", "&gt;");
//		CUSTOM_ESCAPE_TRANSLATOR = new LookupTranslator(CUSTOM);
//		ESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[]{LINEBREAK_ESCAPE_TRANSLATOR, CUSTOM_ESCAPE_TRANSLATOR});
//		CUSTOM_REVERSE.put("&amp;", "&");
//		CUSTOM_REVERSE.put("&lt;", "<");
//		CUSTOM_REVERSE.put("&gt;", ">");
//		CUSTOM_UNESCAPE_TRANSLATOR = new LookupTranslator(CUSTOM_REVERSE);
//		UNESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[]{CUSTOM_UNESCAPE_TRANSLATOR});
//	}
}

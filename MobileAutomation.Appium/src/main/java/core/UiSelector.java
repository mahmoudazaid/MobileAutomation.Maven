package core;

public class UiSelector {
    private String locator = "new UiSelector()";

    public UiSelector resourceId(String value)
    {
        locator += ".resourceId(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector className(String value)
    {
        locator += ".className(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector classNameMatches(String regex)
    {
        locator += ".classNameMatches(\"" + regex + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector text(String value)
    {
        locator += ".text(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector textContains(String value)
    {
        locator += ".textContains(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector index(String value)
    {
        locator += ".index(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector clickable(boolean value)
    {
        locator += ".clickable(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector checked(boolean value)
    {
        locator += ".checked(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector enabled(boolean value)
    {
        locator += ".enabled(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector description(String value)
    {
        locator += ".description(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector descriptionContains(String value)
    {
        locator += ".descriptionContains(\"" + value + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector descriptionMatches(String regex)
    {
        locator += ".descriptionMatches(\"" + regex + "\")";
        System.out.println(locator);
        return this;
    }

    public UiSelector xPath(String xPath)
    {
        locator =xPath;
        System.out.println(locator);
        return this;
    }

    public UiObject makeUiObject()
    {
        return new UiObject(locator);
    }
}

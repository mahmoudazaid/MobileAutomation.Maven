import core.UiSelector;

public class Runner {
    public static void main(String[] args){
        new UiSelector().resourceId("hello").text("item1");
        new UiSelector().className("hello").text("item2");
    }
}

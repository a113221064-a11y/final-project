public class User {
    String name;
    String plan; // "FREE", "PREMIUM"
    int age; // ★ 需求新增
    String region; // ★ 需求新增
    boolean isLoggedIn; // ★ 需求新增
    int currentStreams; // ★ 需求新增：目前正在觀看的裝置數

    public User(String name, String plan, int age, String region) {
        this.name = name;
        this.plan = plan;
        this.age = age;
        this.region = region;
        this.isLoggedIn = true; // 預設登入
        this.currentStreams = 0;
    }

    // 新增：切換方案的方法
    public void setPlan(String newPlan) {
        this.plan = newPlan;
    }

}

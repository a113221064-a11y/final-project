import java.util.List;

public class Video {

    String id;
    String title;
    String category;
    boolean isPremium;      // 是否為付費影片
    int rating;             // ★ 新增：分級 (例如 0, 12, 18)
    List<String> allowedRegions; // ★ 新增：地區清單 (例如 "TW", "US")

    // ★ 請確保建構子參數順序如下，這會影響 Main 怎麼傳資料
    public Video(String id, String title, String category, boolean isPremium, int rating, List<String> allowedRegions) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.isPremium = isPremium;
        this.rating = rating;
        this.allowedRegions = allowedRegions;
    }

    @Override
    public String toString() {
        // 將數字分級轉換為文字標籤
        String ratingLabel;
        if (this.rating >= 18) {
            ratingLabel = "限制級";
        } else if (this.rating >= 12) {
            ratingLabel = "輔導級";
        } else {
            ratingLabel = "普遍級";
        }

        // 組合最終顯示的字串
        return "[" + category + "][" + ratingLabel + "] " + title + (isPremium ? " (VIP)" : "");
    }
}

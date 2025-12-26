import java.util.*;


public class WatchHistoryService {
    // 儲存所有觀看紀錄的清單
// 儲存所有紀錄的清單
    private List<WatchRecord> history = new ArrayList<>();

    // 新增歷史紀錄
    public void addRecord(Video video, int seconds) {
        history.add(new WatchRecord(video, seconds));
    }

    // 印出所有紀錄
    public void showAllHistory() {
        System.out.println("\n--- 您的觀看歷史 ---");
        if (history.isEmpty()) {
            System.out.println("目前沒有紀錄。");
        } else {
            for (WatchRecord r : history) {
                System.out.println("🎬 影片：" + r.video.title + " (看到第 " + r.lastPosition + " 秒)");
            }
        }
    }

    // 推薦邏輯：根據最後看的分類推薦
    public void recommend(List<Video> allVideos) {
        if (history.isEmpty()) {
            System.out.println("推薦：歡迎初次使用，請查看熱門影片！");
            return;
        }

        // 取得最近觀看的一部影片分類
        String lastCategory = history.get(history.size() - 1).video.category;
        System.out.println("\n因為您最近看了 [" + lastCategory + "]，為您推薦：");

        for (Video v : allVideos) {
            if (v.category.equals(lastCategory)) {
                System.out.println("👉 " + v.title);
            }
        }
    }
}

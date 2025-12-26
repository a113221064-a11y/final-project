import java.util.*;

public class Main {
    public static void main(String[] args) {

        // 1. 初始化服務
        VideoPermissionService permissionService = new VideoPermissionService();
        WatchHistoryService historyService = new WatchHistoryService();
        Scanner sc = new Scanner(System.in);


        //模擬登入者
        User currentUser = new User("小明", "FREE", 15, "TW");

        // 2. 準備一些影片資料
        List<Video> videoLibrary = new ArrayList<>();
        videoLibrary.add(new Video("V1", "向日葵", "Music", false, 0, Arrays.asList("TW", "US", "HK")));
        videoLibrary.add(new Video("V2", "Java進階教學", "Coding", true, 12, Arrays.asList("TW")));
        videoLibrary.add(new Video("V3", "法式甜點", "Cooking", false, 0, Arrays.asList("TW", "HK")));
        videoLibrary.add(new Video("V4", "Java入門", "Coding", false, 0, Arrays.asList( "TW","US")));
        videoLibrary.add(new Video("V5", "限制級驚悚", "Horror", true, 18, Arrays.asList("TW")));
        videoLibrary.add(new Video("V6", "青花瓷", "Music", false, 0, Arrays.asList("TW")));
        videoLibrary.add(new Video("V7", "Blue", "Music", false, 0, Arrays.asList("TW")));
        videoLibrary.add(new Video("V8", "Java 教學", "Coding", false, 0, Arrays.asList("HK")));
        videoLibrary.add(new Video("V9", "馬卡龍", "Cooking", false, 0, Arrays.asList("TW")));






        System.out.println("=== 影音平台模擬系統 ===");
        System.out.println("當前方案：" + currentUser.plan);

        while (true) {
            System.out.println("\n---------------------------");
            // 顯示更詳細的使用者狀態，方便測試
            System.out.println("當前使用者：" + currentUser.name + " | 方案：" + currentUser.plan +
                    " | 年齡：" + currentUser.age + " | 地區：" + currentUser.region);
            System.out.println("[選單] 1.影片列表 2.歷史紀錄 3.取得推薦 4.開啟/關閉 PREMIUM 5.離開");
            System.out.print("請選擇：");

            // 預防輸入非數字導致崩潰
            if (!sc.hasNextInt()) {
                System.out.println("請輸入數字選項！");
                sc.next();
                continue;
            }
            int choice = sc.nextInt();

            if (choice == 1) {
                // 1. 顯示影片列表
                for (int i = 0; i < videoLibrary.size(); i++) {
                    System.out.println((i + 1) + ". " + videoLibrary.get(i));
                }
                System.out.print("請選擇編號觀看：");
                int videoIdx = sc.nextInt() - 1;

                if (videoIdx >= 0 && videoIdx < videoLibrary.size()) {
                    Video selected = videoLibrary.get(videoIdx);




                    // ★ 重點更改：使用 try-catch 來處理複雜的權限驗證
                    try {
                        // 呼叫更新後的驗證方法 (會檢查登入、年齡、地區、方案、裝置數)
                        permissionService.validatePlay(currentUser, selected);

                        // 若驗證通過，才會執行到這：
                        System.out.println("🎬 正在播放：" + selected.title);

                        // 模擬觀看行為
                        currentUser.currentStreams++; // 增加同時觀看計數
                        historyService.addRecord(selected, (int) (Math.random() * 600));

                        // 模擬觀看結束（這裡只是簡單範例，實務上會在關閉影片時扣除）
                        System.out.println("(模擬播放結束，釋放裝置位子)");
                        currentUser.currentStreams--;

                    } catch (IllegalAccessException e) {
                        // 捕捉年齡、地區、方案不符的錯誤訊息
                        System.err.println("❌ 存取被拒：" + e.getMessage());
                    } catch (IllegalStateException e) {
                        // 捕捉未登入、裝置數超載的錯誤訊息
                        System.err.println("⚠️ 播放錯誤：" + e.getMessage());
                    }
                }

            } else if (choice == 2) {
                historyService.showAllHistory();

            } else if (choice == 3) {
                historyService.recommend(videoLibrary);

            } else if (choice == 4) {
                // 方案切換邏輯
                System.out.println("\n--- 方案管理 ---");
                if (currentUser.plan.equalsIgnoreCase("FREE")) {
                    System.out.print("是否升級 PREMIUM？(y/n): ");
                    if (sc.next().equalsIgnoreCase("y")) {
                        currentUser.setPlan("PREMIUM");
                        System.out.println("✅ 已升級為 PREMIUM。");
                    }
                } else {
                    System.out.print("是否調降為 FREE？(y/n): ");
                    if (sc.next().equalsIgnoreCase("y")) {
                        currentUser.setPlan("FREE");
                        System.out.println("ℹ️ 已改為 FREE 方案。");
                    }
                }

            } else if (choice == 5) {
                System.out.println("謝謝使用，再見！");
                break;
            }
        }
    }
}
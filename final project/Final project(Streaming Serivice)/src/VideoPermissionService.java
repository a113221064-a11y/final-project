import java.util.Arrays;
import java.util.List;


public class VideoPermissionService {// 這裡用 List 存所有的影片，用 Map 存每個使用者的觀看清單

    // ★ 需求實作：驗證所有觀看條件
    public void validatePlay(User user, Video video) throws IllegalAccessException {

        // 1. 驗證登入
        if (!user.isLoggedIn) {
            throw new IllegalStateException("User must be logged in");
        }

        // 2. 驗證年齡分級
        if (user.age < video.rating) {
            throw new IllegalAccessException("Content is rated " + video.rating + ", user must be " + video.rating + " or older");
        }

        // 3. 驗證地區限制 (假設 Video 內有 allowedRegions)
        if (!video.allowedRegions.contains(user.region)) {
            throw new IllegalAccessException("Content is not available in your region");
        }

        // 4. 驗證訂閱方案
        if (video.isPremium && user.plan.equalsIgnoreCase("FREE")) {
            throw new IllegalAccessException("FreeTier 無法觀看 Premium 內容");
        }

        // 5. 驗證同時觀看裝置數
        int maxStreams = user.plan.equalsIgnoreCase("PREMIUM") ? 4 : 1;
        if (user.currentStreams >= maxStreams) {
            throw new IllegalStateException("Maximum [" + maxStreams + "] simultaneous streams allowed");
        }
    }
}

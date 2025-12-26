public class WatchRecord {

    Video video;
    int lastPosition; // 紀錄上次觀看的秒數

    public WatchRecord(Video video, int lastPosition) {
        this.video = video;
        this.lastPosition = lastPosition;
    }
}

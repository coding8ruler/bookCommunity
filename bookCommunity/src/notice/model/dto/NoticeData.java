package notice.model.dto;

import notice.model.Notice;

public class NoticeData {
	
	private Notice notice;
	
	public NoticeData(Notice notice) {
		this.notice = notice;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	@Override
	public String toString() {
		return "NoticeData [notice=" + notice + "]";
	}
}

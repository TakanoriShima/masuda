package constants;

public enum MessageConst {
    //認証
    I_LOGINED("ログインしました"),
    E_LOGINED("ログインに失敗しました。"),
    I_LOGOUT("ログアウトしました。"),

    //DB更新
    I_REGISTERED("登録が完了しました。"),
    I_UPDATED("更新が完了しました。"),
    I_DELETED("削除が完了しました。"),
    I_FAVORITE("の日報にいいねをしました。"),
    I_UNFAVORITE("の日報のいいねを削除しました。"),

    //バリデーション
    E_NONAME("氏名を入力してください。"),
    E_NOPASSWORD("パスワードを入力してください。"),
    E_NOEMP_CODE("社員番号を入力してください。"),
    E_EMP_CODE_EXIST("入力された社員番号の情報は既に存在しています。"),
    E_NOTITLE("タイトルを入力してください。"),
    E_NOCONTENT("内容を入力してください。"),
    E_NOSTARTTIME("出勤時刻を入力してください"),
    E_NOENDTIME("退勤時刻を入力してください"),
    E_NOSTARTENDTIME("入力値が正しくありません"),
    E_TIME("出勤時刻と退勤時刻が逆転しています");



    private final String text;


    private MessageConst(final String text) {
        this.text = text;
    }


    public String getMessage() {
        return this.text;
    }
}



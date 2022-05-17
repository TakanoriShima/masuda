package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//日報データのDTOモデル

@Table(name = JpaConst.TABLE_REP)
@NamedQueries({
        @NamedQuery(name = JpaConst.Q_REP_GET_ALL, query = JpaConst.Q_REP_GET_ALL_DEF),
        @NamedQuery(name = JpaConst.Q_REP_COUNT, query = JpaConst.Q_REP_COUNT_DEF),
        @NamedQuery(name = JpaConst.Q_REP_GET_ALL_MINE, query = JpaConst.Q_REP_GET_ALL_MINE_DEF),
        @NamedQuery(name = JpaConst.Q_REP_COUNT_ALL_MINE, query = JpaConst.Q_REP_COUNT_ALL_MINE_DEF)

})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity

public class Report {
  //id
    @Id
    @Column(name = JpaConst.REP_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    //日報を登録した従業員　一対多　　従業員に対して日報は複数　Employeeクラス
    //ManyToOne JoinColumnを指定しないとエラー
    //ReportテーブルにEmployeeテーブルのid(主キー)を保持するカラムを作る

    @ManyToOne //Employeeテーブルとの関係　一対多
    @JoinColumn(name= JpaConst.REP_COL_EMP, nullable = false)// employee_idを使ってEmployeeテーブルと接続
    private Employee employee;

    //いつの日報かを示す日付
    @Column(name = JpaConst.REP_COL_REP_DATE, nullable = false)
    private LocalDate reportDate;  //分秒はいらないのでLocalDate

    //日報のタイトル
    @Column(name = JpaConst.REP_COL_TITLE, length = 255, nullable = false)
    private String title;

    //日報の内容
    @Lob //テキストエリアの指定　改行情報を保持
    @Column(name = JpaConst.REP_COL_CONTENT, nullable = false)
    private String content;

    //登録日時
    @Column(name = JpaConst.REP_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    //更新日時

    @Column(name = JpaConst.REP_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    @Column(name= JpaConst.REP_COL_START_TIME,nullable=false)
    private LocalTime startTime;

    @Column(name= JpaConst.REP_COL_END_TIME,nullable=false)
    private LocalTime endTime;




}

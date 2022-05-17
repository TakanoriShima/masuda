package actions;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import models.Employee;
import models.Favorite;
import models.Report;
import services.ReportService;
import utils.DBUtil;

public class FavoriteAction extends ActionBase {

	private ReportService service;

	@Override
	public void process() throws ServletException, IOException {
		service = new ReportService();

		//メソッドを実行
		invoke();
		service.close();

	}

	public void create() throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		// ログインしている従業員情報
		EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

		Employee e = EmployeeConverter.toModel(ev);

		ReportView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

		Report r = ReportConverter.toModel(rv);

		Favorite f = new Favorite();
		f.setEmployee(e);
		f.setReport(r);

		LocalDateTime ldt = LocalDateTime.now();
		f.setCreatedAt(ldt);
		f.setUpdatedAt(ldt);

		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		em.close();

		//セッションに登録完了のフラッシュメッセージを設定
		putSessionScope(AttributeConst.FLUSH, MessageConst.I_FAVORITE.getMessage());

		//一覧画面にリダイレクト
		redirect(ForwardConst.ACT_REP, ForwardConst.CMD_SHOW, rv.getId());

	}

	public void destroy() throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		// ログインしている従業員情報
		EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

		Employee e = EmployeeConverter.toModel(ev);

		ReportView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
		Report r = ReportConverter.toModel(rv);

		Favorite f = em.createNamedQuery(JpaConst.Q_FAV_BY_EMPLOYEE_AND_REPORT, Favorite.class)
				.setParameter("report", r).setParameter("employee", e).getSingleResult();


		em.getTransaction().begin();
		em.remove(f);
		em.getTransaction().commit();
		em.close();

		//セッションに登録完了のフラッシュメッセージを設定
		putSessionScope(AttributeConst.FLUSH, MessageConst.I_UNFAVORITE.getMessage());

		//一覧画面にリダイレクト
		redirect(ForwardConst.ACT_REP, ForwardConst.CMD_SHOW, rv.getId());

	}



}

package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Favorite;

public class FavoriteConverter {
	public static Favorite toModel(FavoriteView fv) {
		return new Favorite(
				fv.getId(),
				EmployeeConverter.toModel(fv.getEmployee()), //EmloyeeView型だから
				ReportConverter.toModel(fv.getReport()),
				fv.getCreatedAt(),
				fv.getUpdatedAt());
	}

	public static FavoriteView toView(Favorite f) {

		if (f == null) {
			return null;
		}

		return new FavoriteView(
				f.getId(),
				EmployeeConverter.toView(f.getEmployee()),
				ReportConverter.toView(f.getReport()),
				f.getCreatedAt(),
				f.getUpdatedAt());
	}

	/**
	 * DTOモデルのリストからViewモデルのリストを作成する
	 * @param list DTOモデルのリスト
	 * @return Viewモデルのリスト
	 */

	public static List<FavoriteView> toViewList(List<Favorite> list) {
		List<FavoriteView> fvs = new ArrayList<>();

		for (Favorite f : list) {
			fvs.add(toView(f));
		}

		return fvs;
	}

}

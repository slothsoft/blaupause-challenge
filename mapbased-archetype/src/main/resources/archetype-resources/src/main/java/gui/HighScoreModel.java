package ${package}.gui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ${package}.${contribName};

public class HighScoreModel extends AbstractTableModel {

	private static final long serialVersionUID = 6708900198232721648L;

	private static final String[] COLUMNS = { "Name", "Author", "#" };
	public static final int COLUMN_NAME = 0;
	public static final int COLUMN_AUTHOR = 1;
	public static final int COLUMN_VICTORIES = 2;

	private List<Row> rows = new ArrayList<>();

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Row row = this.rows.get(rowIndex);
		switch (columnIndex) {
		case COLUMN_NAME:
			return row.name;
		case COLUMN_AUTHOR:
			return row.author;
		case COLUMN_VICTORIES:
			return row.victories;
		default:
			return null;
		}
	}

	@Override
	public int getRowCount() {
		return this.rows.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COLUMN_NAME:
		case COLUMN_AUTHOR:
			return String.class;
		case COLUMN_VICTORIES:
			return String.class;
		default:
			return super.getColumnClass(columnIndex);
		}
	}

	public void propagateWinner(${contribName} winner) {
		Row winnerRow = this.rows.stream().filter(r -> r.name.equals(winner.getDisplayName())).findFirst()
				.orElse(new Row(winner.getDisplayName(), winner.getAuthor()));
		winnerRow.victories++;
		if (!this.rows.contains(winnerRow)) {
			this.rows.add(winnerRow);
		}
		this.rows.sort(Comparator.<Row> comparingInt(e -> e.victories).reversed());
		fireTableDataChanged();
	}

	/*
	 * 
	 */

	static class Row {

		final String name;
		final String author;
		int victories;

		public Row(String name, String author) {
			this.name = name;
			this.author = author;
		}

	}

}

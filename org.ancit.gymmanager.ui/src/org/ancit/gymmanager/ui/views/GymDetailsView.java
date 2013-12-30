package org.ancit.gymmanager.ui.views;

import org.ancit.gymmanager.model.GymManager;
import org.ancit.gymmanager.ui.filter.SearchFilter;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

public class GymDetailsView extends ViewPart {

	public static final String ID = "org.ancit.gymmanager.ui.views.GymDetailsView"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Table table;
	private Text txtSearchHere;
	private TableViewer tableViewer;
	SearchFilter searchFilter;

	public GymDetailsView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {

		Composite composite_1 = formToolkit.createComposite(parent, SWT.NONE);
		formToolkit.paintBordersFor(composite_1);
		composite_1.setLayout(new GridLayout(1, false));

		Section sctnNewSection = formToolkit.createSection(composite_1,
				Section.TWISTIE | Section.TITLE_BAR);
		GridData gd_sctnNewSection = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1);
		gd_sctnNewSection.heightHint = 60;
		sctnNewSection.setLayoutData(gd_sctnNewSection);
		sctnNewSection.setBounds(0, 0, 98, 21);
		formToolkit.paintBordersFor(sctnNewSection);
		sctnNewSection.setText("Type to Search or Filter");
		sctnNewSection.setExpanded(true);

		Composite composite_2 = new Composite(sctnNewSection, SWT.NONE);
		formToolkit.adapt(composite_2);
		formToolkit.paintBordersFor(composite_2);
		sctnNewSection.setClient(composite_2);
		composite_2.setLayout(new GridLayout(2, false));
		{
			Label lblSearchMember = new Label(composite_2, SWT.NONE);
			lblSearchMember.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
					false, false, 1, 1));
			formToolkit.adapt(lblSearchMember, true, true);
			lblSearchMember.setText("Search Member :");
		}

		txtSearchHere = new Text(composite_2, SWT.BORDER);
		txtSearchHere.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchFilter.setSearch(txtSearchHere.getText().toString());
				tableViewer.refresh();
			}
		});
		txtSearchHere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		formToolkit.adapt(txtSearchHere, true, true);
		{
			Section sctnGymData = formToolkit.createSection(composite_1,
					Section.TITLE_BAR);
			sctnGymData.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
					true, 1, 1));
			formToolkit.paintBordersFor(sctnGymData);
			sctnGymData.setText("Gym Data");
			sctnGymData.setExpanded(true);
			{
				Composite composite = new Composite(sctnGymData, SWT.NONE);
				formToolkit.adapt(composite);
				formToolkit.paintBordersFor(composite);
				sctnGymData.setClient(composite);
				composite.setLayout(new GridLayout(1, false));
				{
					tableViewer = new TableViewer(composite, SWT.BORDER
							| SWT.FULL_SELECTION);
					table = tableViewer.getTable();
					table.setLinesVisible(true);
					table.setHeaderVisible(true);

					table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
							true, 1, 1));
					formToolkit.paintBordersFor(table);
					{
						TableViewerColumn tableViewerColumn = new TableViewerColumn(
								tableViewer, SWT.NONE);
						TableColumn tblclmnId = tableViewerColumn.getColumn();
						tblclmnId.setWidth(50);
						tblclmnId.setText("Id");
					}
					{
						TableViewerColumn tableViewerColumn = new TableViewerColumn(
								tableViewer, SWT.NONE);
						TableColumn tblclmnMemberName = tableViewerColumn
								.getColumn();
						tblclmnMemberName.setWidth(123);
						tblclmnMemberName.setText("Member Name");
					}
					{
						TableViewerColumn tableViewerColumn = new TableViewerColumn(
								tableViewer, SWT.NONE);
						TableColumn tblclmnMobileNo = tableViewerColumn
								.getColumn();
						tblclmnMobileNo.setWidth(125);
						tblclmnMobileNo.setText("Mobile No");
					}
					{
						TableViewerColumn tableViewerColumn = new TableViewerColumn(
								tableViewer, SWT.NONE);
						TableColumn tblclmnStartDate = tableViewerColumn
								.getColumn();
						tblclmnStartDate.setWidth(110);
						tblclmnStartDate.setText("Start Date");
					}
					{
						TableViewerColumn tableViewerColumn = new TableViewerColumn(
								tableViewer, SWT.NONE);
						TableColumn tblclmnEndDate = tableViewerColumn
								.getColumn();
						tblclmnEndDate.setWidth(100);
						tblclmnEndDate.setText("End Date");
					}
					{
						TableViewerColumn tableViewerColumn = new TableViewerColumn(
								tableViewer, SWT.NONE);
						TableColumn tblclmnPlan = tableViewerColumn.getColumn();
						tblclmnPlan.setWidth(100);
						tblclmnPlan.setText("Plan");
					}
				}
			}
		}

		getSite().setSelectionProvider(tableViewer);
		tableViewer.setContentProvider(new GymDetailsContentProvider());
		tableViewer.setLabelProvider(new GymDetailsLabelProvider());
		GymManager manager = GymManager.getGymManager();
		tableViewer.setInput(manager.getRecord());
		searchFilter = new SearchFilter();
		tableViewer.addFilter(searchFilter);

	}

	@Override
	public void setFocus() {

	}

}

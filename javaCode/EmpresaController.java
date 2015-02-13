package Controller;

import Entity.Empresa;
import Entity.util.JsfUtil;
import Entity.util.PaginationHelper;
import Facade.EmpresaFacade;
import java.io.FileOutputStream;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

@ManagedBean(name = "empresaController")
@SessionScoped
public class EmpresaController implements Serializable {

    private Empresa current;
    private DataModel items = null;
    @EJB
    private Facade.EmpresaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    public final static Short ZERO_SHORT = 0;
    public final static Short ONE_SHORT = 1;
    public final static Short TWO_SHORT = 2;
    public final static Short THREE_SHORT = 3;
    public final static Short FOUR_SHORT = 4;
    public final static Short FIVE_SHORT = 5;
    public final static Short SIX_SHORT = 6;
    public final static Short SEVEN_SHORT = 7;
    public final static Short EIGHT_SHORT = 8;
    public final static Short NINE_SHORT = 9;
    public final static Short FIVE_HUNDRED_SHORT = 5000;
    public final static Short TEN_SHORT = 10;
    public final static Short ELEVEN_SHORT = 11;
    public final static Short TWELVE_SHORT = 12;
    public final static Short THIRTEEN_SHORT = 13;
    public final static Short FOURTEEN_SHORT = 14;
    public final static Short FIFTENN_SHORT = 15;
    public final static Short SIXTEEN_SHORT = 16;
    public EmpresaController() {
    }
    
    public Empresa idEmpresa(){
        Empresa empresa = new Empresa();
        List<Empresa> e;
        Empresa id = new Empresa();
        e= ejbFacade.lastID();
        Iterator<Empresa> it = e.iterator();
        while(it.hasNext()){
            empresa =it.next();
            id = empresa;
        }
        return id;
    }

    public Empresa getSelected() {
        if (current == null) {
            current = new Empresa();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EmpresaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        else
        {
          pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };  
        }
        return pagination;
    }
     public PaginationHelper getPaginationByEstado() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listByEstado());
                }
            };
        }
        else
        {
             pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listByEstado());
                }
            };
        }
        return pagination;
    }
      public PaginationHelper getPaginationByEstadoA() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listByEstadoA());
                }
            };
        }
        else
        {
             pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listByEstadoA());
                }
            }; 
        }
        return pagination;
    }
       public PaginationHelper getPaginationByEstadoR() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listByEstadoR());
                }
            };
        }
        else
        {
          pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listByEstadoR());
                }
            }; 
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Empresa) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Empresa();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EmpresaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String createh() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EmpresaCreated"));
            return prepareCreateh();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String prepareCreateh() {
        current = new Empresa();
        current.getIdEmpresa();
        selectedItemIndex = -1;
        return "registroUsuarioHome";
    }
    
    public String createa() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EmpresaCreated"));
            return prepareCreatea();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String prepareCreatea() {
        current = new Empresa();
        selectedItemIndex = -1;
        return "crearUsuarioAdministrador";
    }
    
    public Integer enviaIdEmpresaH (int id)
    {
        int idEmpresaHImport = id;
        return idEmpresaHImport;
    }

    public String prepareEdit() {
        current = (Empresa) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "estadoEmpresaAdministrador";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EmpresaUpdated"));
            return "/administrador/indexAdministrador";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Empresa) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EmpresaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        else
        {
            items = getPagination().createPageDataModel(); 
        }
        return items;
    }
     public DataModel getItemsA() {
        if (items == null) {
            items = getPaginationByEstadoA().createPageDataModel();
        }
        else
        {
            items = getPaginationByEstadoA().createPageDataModel();
        }
        return items;
    }
      public DataModel getItemsR() {
        if (items == null) {
            items = getPaginationByEstadoR().createPageDataModel();
        }
        else
        {
            items = getPaginationByEstadoR().createPageDataModel();
        }
        return items;
    }
     public DataModel getItemsByEstado() {
        if (items == null) {
            items = getPaginationByEstado().createPageDataModel();
        }
        else{
            items = getPaginationByEstado().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
   public SelectItem[] getItemsAvailableSelectOnew() {
        return JsfUtil.getSelectItems(ejbFacade.lastID(), true);
        
    }
   public SelectItem[] getItemsAvailableSelectManyidEmpresa() {
        return JsfUtil.getSelectItems(ejbFacade.lastID(), false);
   }
  
    public void Excel() throws Exception {
    	
    	int pagina = 1;    
    	List<Empresa> l; 
        l = ejbFacade.listaTodo();
        HSSFWorkbook xls = generarAllRowsExcel(l);
     //   response.setContentType("application/vnd.ms-excel");
    //	response.addHeader("Content-Disposition", "attachment; filename=reporte.xls");
        String filename = "C:/excel/Empresas.xls";
       FileOutputStream fileOut = new FileOutputStream(filename);
   xls.write(fileOut);
        fileOut.close();
   
	
    }   
        public HSSFWorkbook generarAllRowsExcel(List<Empresa> l)throws Exception{
		
    	//Se crea el excel
	    HSSFWorkbook xlsIn = new HSSFWorkbook();
	    HSSFSheet sheet = xlsIn.createSheet("Reporte de Empresas");
	    Empresa dto2 = new Empresa();
	    boolean mensaje_def=false;

        sheet.setColumnWidth(ZERO_SHORT, FIVE_HUNDRED_SHORT );
        sheet.setColumnWidth(ONE_SHORT, FIVE_HUNDRED_SHORT );
        sheet.setColumnWidth(TWO_SHORT, FIVE_HUNDRED_SHORT );
        sheet.setColumnWidth(THREE_SHORT, FIVE_HUNDRED_SHORT );
        sheet.setColumnWidth(FOUR_SHORT, FIVE_HUNDRED_SHORT );
        sheet.setColumnWidth(FIVE_HUNDRED_SHORT, FIVE_HUNDRED_SHORT );
        sheet.setColumnWidth(SIX_SHORT, FIVE_HUNDRED_SHORT );
        sheet.setColumnWidth(SEVEN_SHORT, FIVE_HUNDRED_SHORT );
       
        
        HSSFCellStyle  estiloTit = xlsIn.createCellStyle( );
        HSSFFont fuenteTit = xlsIn.createFont( );
        fuenteTit.setFontHeightInPoints(ELEVEN_SHORT);
        fuenteTit.setFontName("Arial");
        fuenteTit.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fuenteTit.setColor(HSSFColor.WHITE.index);
        
        estiloTit.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        estiloTit.setBottomBorderColor(HSSFColor.BLACK.index);
        estiloTit.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        estiloTit.setLeftBorderColor(HSSFColor.BLACK.index);
        estiloTit.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        estiloTit.setRightBorderColor(HSSFColor.BLACK.index);
        estiloTit.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        estiloTit.setTopBorderColor(HSSFColor.BLACK.index);
        estiloTit.setFont(fuenteTit);

        estiloTit.setFillForegroundColor(HSSFColor.BLUE.index);
        estiloTit.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    
        //Crea estilo para celda en general
        HSSFCellStyle  estiloCelda = xlsIn.createCellStyle( );
       
        estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        estiloCelda.setBottomBorderColor(HSSFColor.BLACK.index);
        estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        estiloCelda.setLeftBorderColor(HSSFColor.BLACK.index);
        estiloCelda.setBorderRight(HSSFCellStyle.BORDER_THIN);
        estiloCelda.setRightBorderColor(HSSFColor.BLACK.index);
        estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THIN);
        estiloCelda.setTopBorderColor(HSSFColor.BLACK.index);
        
        HSSFRichTextString det = null;
        HSSFRichTextString detNomxiAll = null;
        HSSFCell cellDet = null;  
        HSSFRichTextString det2 = null;
        HSSFCell cellDet2 = null;  
        HSSFCell cellDetNomxiAll = null; 
        
        //Crea nombres de las columnas
        int i = 0;
        int j = 0;	    	
    		
		HSSFRow rowDet = sheet.createRow(i++);	  
		cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
        det = new HSSFRichTextString("ID");
        cellDet.setCellStyle(estiloTit);
        cellDet.setCellValue(det);
          
		cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
        det = new HSSFRichTextString("NOMBRE");
        cellDet.setCellStyle(estiloTit);
        cellDet.setCellValue(det);
          
		cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
        det = new HSSFRichTextString("DIRECCION");
        cellDet.setCellStyle(estiloTit);
        cellDet.setCellValue(det);
        
		cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
        det = new HSSFRichTextString("CIUDAD");
        cellDet.setCellStyle(estiloTit);
        cellDet.setCellValue(det);
        
		cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
        det = new HSSFRichTextString("REGION");
        cellDet.setCellStyle(estiloTit);
        cellDet.setCellValue(det);
        	  
		cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
        det = new HSSFRichTextString("TELEFONO");
        cellDet.setCellStyle(estiloTit);
        cellDet.setCellValue(det);
        
		cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
        det = new HSSFRichTextString("ESTADO");
        cellDet.setCellStyle(estiloTit);
        cellDet.setCellValue(det);
          
	 i = 1 ;
    	if(l.isEmpty()){
    		while(i < 50){
    			rowDet = sheet.createRow(i++);
    			j = 0 ;
    			while(j < 9){
    				cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());   
    				cellDet.setCellValue(new HSSFRichTextString(""));
    			}    				
    		}
    	}
        
        Iterator<Empresa> it = l.iterator();
        
        	while(it.hasNext()){	 
//			}
                    dto2 = it.next();
    		j = 0;
			//Se crea una fila
			rowDet = sheet.createRow(i++);	
			
			//N MOC
			cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
			String nMoc = String.valueOf(dto2.getIdEmpresa());
			det = new HSSFRichTextString(nMoc);
			cellDet.setCellStyle(estiloCelda);            
			cellDet.setCellValue(det);
			
			//Date
			cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
			det = new HSSFRichTextString(dto2.getNombreEmpresa());
			cellDet.setCellStyle(estiloCelda);       
			cellDet.setCellValue(det);
			
			//A/C
			cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
			det = new HSSFRichTextString(dto2.getDireccionEmpresa());
			cellDet.setCellStyle(estiloCelda);       
			cellDet.setCellValue(det);
			
			//FLEET
			cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
                        String nciu = String.valueOf(dto2.getNombreCiudad());
			det = new  HSSFRichTextString(nciu);
			cellDet.setCellStyle(estiloCelda);       
			cellDet.setCellValue(det);
			 
			//MFL
			cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
                        String reg = String.valueOf(dto2.getNombreRegion());
			det = new  HSSFRichTextString(reg);
			cellDet.setCellStyle(estiloCelda);       
			cellDet.setCellValue(det);
			
			//CLB
			cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
			String tel = String.valueOf(dto2.getNombreRegion());
			det = new  HSSFRichTextString(tel);
			cellDet.setCellStyle(estiloCelda);       
			cellDet.setCellValue(det);
			
			//DEFERRAL CLASS
			cellDet = rowDet.createCell(Integer.valueOf(j++).shortValue());
			det = new HSSFRichTextString(dto2.getEstadoEmpresa());
			cellDet.setCellStyle(estiloCelda);       
			cellDet.setCellValue(det);			
			
                }
                return xlsIn;

        }

    @FacesConverter(forClass = Empresa.class)
    public static class EmpresaControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmpresaController controller = (EmpresaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "empresaController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Empresa) {
                Empresa o = (Empresa) object;
                return getStringKey(o.getIdEmpresa());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Empresa.class.getName());
            }
        }
    }
}
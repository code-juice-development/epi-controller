package epis.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import epis.models.DataObject;
import epis.models.Employee;
import epis.models.Epi;
import epis.models.ReportEmployeeEpi;
import epis.utils.MessageUtil;
import epis.view.ViewEdit;
import epis.view.ViewEditReportEmployeeEpi;
import epis.view.components.DirectoryChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Conmtroller class of Report Employee x Epi
 * @author Jean Poffo
 * @since  03/09/2020  
 */
public class ControllerReportEmployeeEpi extends ControllerMaintenance {

    private DirectoryChooser directoryChooser;

    public ControllerReportEmployeeEpi() {
        this.directoryChooser = new DirectoryChooser("Selecione o local para salvar os relatórios: ");
    }
    
    @Override
    public void processUpdateData() {}

    @Override
    public void processActionAdd(DataObject entity) {
        ReportEmployeeEpi reportEmployeeEpi = (ReportEmployeeEpi) entity;
        
        if(directoryChooser.callSelectDirectory()) {
            new Thread(() -> {
                this.processReportControllEpi(reportEmployeeEpi);
                this.processReportListEpi(reportEmployeeEpi);
                
                MessageUtil.information("Relatório gerado com sucesso", "Relatório");
            }).start();
        }
        else {
            MessageUtil.warning("Não foi selecionado nenhum diretório", "Erro");
        }
    }
    
    private void processReportControllEpi(ReportEmployeeEpi reportEmployeeEpi) {
        Document document = new Document();
        
        try {    
            PdfWriter.getInstance(document, new FileOutputStream(new File(this.directoryChooser.getSelectedFile(), "Relatório Controle de EPI.pdf")));
            
            document.open();
            
            for (Employee employee : reportEmployeeEpi.getEmployees()) {
                Paragraph title = new Paragraph("Controle de Entrega de EPI's", new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD));
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(30);
                
                document.add(title);
                document.add(new Paragraph("RT Tratores Comércio de Peças e Serviços LTDA", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                document.add(new Paragraph("Rod. BR 470 KM 132, nº 5260 Margem Esquerda - Lontras", new Font(Font.FontFamily.HELVETICA, 12)));
                
                PdfPTable tableEmployeeData = new PdfPTable(4);
                tableEmployeeData.setSpacingBefore(15);
                tableEmployeeData.setSpacingAfter(15);
                tableEmployeeData.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableEmployeeData.setTotalWidth(PageSize.A4.getWidth() - 80);
                tableEmployeeData.setLockedWidth(true);
                tableEmployeeData.getDefaultCell().setFixedHeight(20);
                
                tableEmployeeData.addCell(new Phrase("Funcionário:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                tableEmployeeData.addCell(new Phrase(employee.getName()));
                tableEmployeeData.addCell(new Phrase("CPF:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                tableEmployeeData.addCell(new Phrase(employee.getCpf()));
                tableEmployeeData.addCell(new Phrase("Função:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                tableEmployeeData.addCell(new Phrase(employee.getOccupation()));
                tableEmployeeData.addCell(new Phrase("Data Abertura:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                tableEmployeeData.addCell(new Phrase(reportEmployeeEpi.getInitialDate()));
                
                document.add(tableEmployeeData);
                
                document.add(new Paragraph("Nos termos da Lei 6.514 DE 22/12/1977, Art. 158 da CLT, da " +
                    "Portaria 3.214 00 04/06/1978, NR. 6.7.1, o servidor, fica responsável pelo equipamento " +
                    "recebido da RT Tratores Comércio de Peças e Serviços, estando ainda certo que, em caso " +
                    "de DANOS e PERDAS pôr comprovada negligência e/ou imprudência, " +
                    "ficará obrigado ao ressarcimento do patrimônio " +
                    "da Instituição. Declara ainda estar ciente da obrigatoriedade de uso dos equipamentos " +
                    "ora recebidos e das penalidades cabíveis no caso de infração ao Art. 158 da CLT" +
                    " estando sujeito as sanções do ART 482 (Ato faltoso).",
                new Font(Font.FontFamily.HELVETICA, 12)));
                
                PdfPTable tableEmployeeEpis = new PdfPTable(4);
                tableEmployeeEpis.setWidthPercentage(100);
                tableEmployeeEpis.setWidths(new float[] { 21f, 14.5f, 14.5f, 50f });
                tableEmployeeEpis.setTotalWidth(PageSize.A4.getWidth() - 80);
                tableEmployeeEpis.setLockedWidth(true);
                tableEmployeeEpis.setSpacingBefore(15);
                tableEmployeeEpis.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableEmployeeEpis.getDefaultCell().setFixedHeight(35);
                tableEmployeeEpis.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                tableEmployeeEpis.setHeaderRows(1);
                
                tableEmployeeEpis.addCell(new Phrase("Descrição", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                tableEmployeeEpis.addCell(new Phrase("Código CA", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                tableEmployeeEpis.addCell(new Phrase("Data", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                tableEmployeeEpis.addCell(new Phrase("Assinatura", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                
                List episToOrder = new ArrayList();
                
                for (Epi epi : employee.getEpis()) {
                    LocalDate localDateInitial = LocalDate.parse(reportEmployeeEpi.getInitialDate(), DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                    LocalDate localDateFinal = LocalDate.parse(reportEmployeeEpi.getFinalDate(), DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                    
                    do {
                        episToOrder.add(new Object[] {epi, localDateInitial});
                        
                        localDateInitial = localDateInitial.plusDays(epi.getFrequency());
                        
                    } while(DAYS.between(localDateInitial, localDateFinal) >= 0);
                }  
                
                LocalDate dataAtual;
                LocalDate proximaData;
                Object[] atual;
                Object[] proximo;
                
                // Bubble Sort = Dates
                for (int i = 0; i < episToOrder.size(); i++) {
                    for (int j = 0; j < episToOrder.size() - i - 1; j++) {
                        atual = (Object[]) episToOrder.get(j);
                        proximo = (Object[]) episToOrder.get(j + 1);
                        
                        dataAtual = (LocalDate) atual[1];
                        proximaData = (LocalDate) proximo[1];                        

                        if (dataAtual.compareTo(proximaData) > 0) {
                            episToOrder.set(j + 1, atual);
                            episToOrder.set(j, proximo);
                        }
                    }
                }
                
                for (Object object : episToOrder) {
                    Object[] actual = (Object[]) object;
                    
                    Epi epi = (Epi) actual[0];
                    LocalDate date = (LocalDate) actual[1];
                    
                    tableEmployeeEpis.addCell(new Phrase(epi.getName()));
                    tableEmployeeEpis.addCell(new Phrase(epi.getCodeCa()));
                    tableEmployeeEpis.addCell(new Phrase(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                    tableEmployeeEpis.addCell(new Phrase(""));   
                }
                
                document.add(tableEmployeeEpis);
                
                document.newPage();
            }
            
            document.close();
        } 
        catch (DocumentException | FileNotFoundException e) {}
    }
    
    private void processReportListEpi(ReportEmployeeEpi reportEmployeeEpi) {
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(this.directoryChooser.getSelectedFile(), "Relatório Lista de Compras de EPI.pdf")));
            document.open();

            Paragraph title = new Paragraph("Lista de Compras de EPI's", new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
                    
            document.add(title);
            
            PdfPTable tableDate = new PdfPTable(4);
            tableDate.setSpacingBefore(15);
            tableDate.setSpacingAfter(15);
            tableDate.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableDate.setTotalWidth(PageSize.A4.getWidth() - 80);
            tableDate.setLockedWidth(true);
            tableDate.getDefaultCell().setFixedHeight(20);
            
            tableDate.addCell(new Phrase("Data Inicial:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            tableDate.addCell(new Phrase(reportEmployeeEpi.getInitialDate()));
            tableDate.addCell(new Phrase("Data Final:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            tableDate.addCell(new Phrase(reportEmployeeEpi.getFinalDate()));

            document.add(tableDate);
            
            PdfPTable tableEpis = new PdfPTable(3);
            tableEpis.setWidthPercentage(100);
            tableEpis.setWidths(new float[] { 60, 20, 20 });
            tableEpis.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableEpis.setTotalWidth(PageSize.A4.getWidth() - 80);
            tableEpis.setLockedWidth(true);
            tableEpis.getDefaultCell().setFixedHeight(20);
            
            tableEpis.addCell(new Phrase("Descrição", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            tableEpis.addCell(new Phrase("Código CA", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            tableEpis.addCell(new Phrase("Quantidade", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            
            Map itemQuantidade = new HashMap<String, Integer>();
            
            for (Employee employee : reportEmployeeEpi.getEmployees()) {
                for (Epi epi : employee.getEpis()) {
                    LocalDate localDateInitial = LocalDate.parse(reportEmployeeEpi.getInitialDate(), DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                    LocalDate localDateFinal = LocalDate.parse(reportEmployeeEpi.getFinalDate(), DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                    
                    do {
                        String key = epi.getName() + "%" + epi.getCodeCa();
                        
                        int amount = 1;
                        
                        if(itemQuantidade.get(key) != null) {
                            amount = (int) itemQuantidade.get(key) + 1;
                        }

                        itemQuantidade.put(key, amount);
                        
                        localDateInitial = localDateInitial.plusDays(epi.getFrequency());
                        
                    } while(DAYS.between(localDateInitial, localDateFinal) >= 0);
                }
            }
            
            for (Iterator it = itemQuantidade.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String,Integer> pair = (Map.Entry<String,Integer>) it.next();
                
                String[] nameAndCode = String.valueOf(pair.getKey()).split("%");
                String quantity = String.valueOf(pair.getValue());
                
                tableEpis.addCell(new Phrase(nameAndCode[0]));
                tableEpis.addCell(new Phrase(nameAndCode[1]));
                tableEpis.addCell(new Phrase(quantity));
            }
            
            document.add(tableEpis);

            document.close();
        } 
        catch (DocumentException | FileNotFoundException e) {}
    }
    
    @Override
    public ViewEdit getViewEdit(DataObject entity, ProcessAction process) {
        return new ViewEditReportEmployeeEpi(entity, process);
    }

    @Override
    public DataObject getDataObjectModel() {
        return new ReportEmployeeEpi();
    }
    
}

package org.dcwloadassurant.com.Dcwload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    private List<ReportRow> reportRows = new ArrayList<>();

    public ReportController() {
        reportRows.add(new ReportRow("Product A", 100, 150));
        reportRows.add(new ReportRow("Product B", 200, 250));
    }

    @GetMapping("/report")
    public String getReport(Model model) {
        model.addAttribute("reportRows", reportRows);
        return "report";
    }

    @PostMapping("/report/update")
    public String updateReport(@ModelAttribute ReportRow updatedRow, Model model) {
        reportRows.set(updatedRow.getIndex(), updatedRow);
        model.addAttribute("reportRows", reportRows);
        return "redirect:/report";
    }

    static class ReportRow {
            private int index;
            private String productName;
            private int salesMay;
            private int salesJune;

        // Constructors, getters, and setters
        public ReportRow() {}

        public ReportRow(String productName, int salesMay, int salesJune) {
            this.productName = productName;
            this.salesMay = salesMay;
            this.salesJune = salesJune;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getSalesMay() {
            return salesMay;
        }

        public void setSalesMay(int salesMay) {
            this.salesMay = salesMay;
        }

        public int getSalesJune() {
            return salesJune;
        }

        public void setSalesJune(int salesJune) {
            this.salesJune = salesJune;
        }
    }
}

package com.trilogyed.JyotiChavanU1Capstone.service;

import com.trilogyed.JyotiChavanU1Capstone.dao.*;
import com.trilogyed.JyotiChavanU1Capstone.exception.NotFoundException;
import com.trilogyed.JyotiChavanU1Capstone.model.*;
import com.trilogyed.JyotiChavanU1Capstone.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class InvoiceViewModelServiceLayer {
    private InvoiceDao invoiceDao;
    private SalesTaxDao salesTaxDao;
    private ProcessingFeeDao processingFeeDao;
    private GameDao gameDao;
    private ConsoleDao consoleDao;
    private TShirtDao tShirtDao;
    int inventoryQuantity = 0;

    @Autowired
    public InvoiceViewModelServiceLayer(InvoiceDao invoiceDao, SalesTaxDao salesTaxDao, ProcessingFeeDao processingFeeDao, GameDao gameDao, ConsoleDao consoleDao, TShirtDao tShirtDao) {
        this.invoiceDao = invoiceDao;
        this.salesTaxDao = salesTaxDao;
        this.processingFeeDao = processingFeeDao;
        this.gameDao = gameDao;
        this.consoleDao = consoleDao;
        this.tShirtDao = tShirtDao;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {

        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice.setQuantity(invoiceViewModel.getQuantity());
        invoice.setItem_type(invoiceViewModel.getItem_type());
        invoice.setItem_id(invoiceViewModel.getItem_id());
        invoice.setUnit_price(invoiceViewModel.getUnitPrice());
        invoice.setQuantity(invoiceViewModel.getQuantity());
        invoice.setSubtotal(invoiceViewModel.getSubtotal());
        invoice.setTax(invoiceViewModel.getSalesTax());
        invoice.setProcessing_fee(invoiceViewModel.getProcessingFee());
        invoice.setTotal(invoiceViewModel.getTotal());

        invoice = invoiceDao.addInvoice(invoice);

        invoiceViewModel.setInvoiceId(invoice.getInvoice_id());

        return invoiceViewModel;

    }

//    @Transactional
//    public InvoiceViewModel getInvoice(int id){
//        Invoice invoice = invoiceDao.getInvoice(id);
//
//        return processPurchase();
//
//    }


    @Transactional
    public InvoiceViewModel processPurchase(PurchasingItem purchasingItem) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName(purchasingItem.getName());
        invoiceViewModel.setStreet(purchasingItem.getStreet());
        invoiceViewModel.setCity(purchasingItem.getCity());
        invoiceViewModel.setState(purchasingItem.getState());
        invoiceViewModel.setZipcode(purchasingItem.getZipcode());
        Item item = getTheItemFromTheDatabase(purchasingItem.getItem_type(), purchasingItem.getItem_id());
        invoiceViewModel.setItem_type(item.getItem_type());
        if (purchasingItem.getItem_type().equalsIgnoreCase("Game")) {
            invoiceViewModel.setItem_id(invoiceViewModel.getGame().getGame_id());
            invoiceViewModel.setQuantity(invoiceViewModel.getGame().getQuantity());
            invoiceViewModel.setUnitPrice(invoiceViewModel.getUnitPrice());
        } else if ((purchasingItem.getItem_type().equalsIgnoreCase("Console"))) {
            invoiceViewModel.setItem_id(invoiceViewModel.getConsole().getConsole_id());
            invoiceViewModel.setQuantity(invoiceViewModel.getConsole().getQuantity());
            invoiceViewModel.setUnitPrice(invoiceViewModel.getUnitPrice());

        } else if ((purchasingItem.getItem_type().equalsIgnoreCase("TShirt"))) {
            invoiceViewModel.setItem_id(invoiceViewModel.gettShirt().getT_shirt_id());
            invoiceViewModel.setQuantity(invoiceViewModel.gettShirt().getQuantity());
            invoiceViewModel.setUnitPrice(invoiceViewModel.getUnitPrice());
        }
        invoiceViewModel.setSubtotal(invoiceViewModel.getUnitPrice().multiply(new BigDecimal(purchasingItem.getQuantity())));
        invoiceViewModel.setSalesTax(calculateTax(invoiceViewModel.getSubtotal(),purchasingItem.getState()));
        invoiceViewModel.setProcessingFee(getProcessingFee(item.getItem_type(), item.getQuantity()));
        invoiceViewModel.setTotal(invoiceViewModel.getSubtotal().add(invoiceViewModel.getSalesTax().add(invoiceViewModel.getProcessingFee())));
        return invoiceViewModel;

    }

        private  BigDecimal getProcessingFee(String item_type, int quantity) {
         BigDecimal processingFee = new BigDecimal(0.00) ;
         if(quantity <10){
            if(item_type.equalsIgnoreCase("Game")){
            processingFee = BigDecimal.valueOf(processingFeeDao.getProcessingFeeByProductType("Game"));
            } else if (item_type.equalsIgnoreCase("Console")){
            processingFee = BigDecimal.valueOf(processingFeeDao.getProcessingFeeByProductType("Console"));
            } else if (item_type.equalsIgnoreCase("TShirt")){
            processingFee = BigDecimal.valueOf(processingFeeDao.getProcessingFeeByProductType("TShirt"));
            }
         }else if (quantity > 10){
             if(item_type.equalsIgnoreCase("Game")){
                 processingFee = BigDecimal.valueOf(processingFeeDao.getProcessingFeeByProductType("Game")).add(new BigDecimal(15.49));
             } else if (item_type.equalsIgnoreCase("Console")){
                 processingFee = BigDecimal.valueOf(processingFeeDao.getProcessingFeeByProductType("Console")).add(new BigDecimal(15.49));
             } else if (item_type.equalsIgnoreCase("TShirt")){
                 processingFee = BigDecimal.valueOf(processingFeeDao.getProcessingFeeByProductType("TShirt")).add(new BigDecimal(15.49));
             }

         }
        return  processingFee;
    }



     List<String> stateList = Arrays.asList(
                    "AL", "AK" ,"AZ", "AR", "CA" ,"CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
                     "LA", "ME", "MD","MA" ,"MI", "MN", "MS", "MO","MT", "NE", "NV", "NH", "NJ", "NM","NY", "NC","ND",
                     "OH","OK", "OR", "PA", "RI", "SC", "SD", "TN","TX","UT", "VT","VA", "WA", "WV", "WI", "WY");


      private BigDecimal calculateTax(BigDecimal amount , String state) {
         for (String i : stateList) {
             if (state.equals(i)){
                 BigDecimal taxRate = salesTaxDao.getRateByState(state).getRate();
                 BigDecimal tax = amount.multiply(taxRate);
                 return tax;
             }
         }
            throw new NotFoundException("State Code input" + state + "is not found");
     }

     private Item getTheItemFromTheDatabase(String item_type, int item_id) {

        Item item = new Item();
        if (!(item.getItem_type().equalsIgnoreCase("Game")
                || item.getItem_type().equalsIgnoreCase("Console"))
                || item.getItem_type().equalsIgnoreCase("TShirt")){
            throw new NotFoundException("Item type must be one of these : Game, Console, TShirt");
        }
        else if(item_type == "Game"){
            item = gameDao.getGame(item_id);
        }else if(item_type == "Console"){
            item = consoleDao.getConsole(item_id);
        }else{
           item = tShirtDao.getTShirt(item_id);
        }
        return item;
    }

    private boolean updateInventory(PurchasingItem purchasingItem, int inventoryQuantity){
          if(purchasingItem.getItem_type().equalsIgnoreCase("Game")){
              Game game = gameDao.getGame(purchasingItem.getItem_id());
              game.setQuantity(inventoryQuantity);
              gameDao.updateGame(game);
          } else if (purchasingItem.getItem_type().equalsIgnoreCase("Console")){
              Console console = consoleDao.getConsole(purchasingItem.getItem_id());
              console.setQuantity(inventoryQuantity);
              consoleDao.updateConsole(console);
          }else if (purchasingItem.getItem_type().equalsIgnoreCase("TShirt")){
              TShirt tShirt = tShirtDao.getTShirt(purchasingItem.getItem_id());
              tShirt.setQuantity(inventoryQuantity);
              tShirtDao.updateTShirt(tShirt);
          }else {
              return false;
          }
          return true;
    }

}

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class AddPurchaseToTrashTest extends BaseTest {

    @Test
    @Parameters({"login", "password", "product"})
    public void addPurchaseToTrash(String login, String password, String product) {
        MainForm mainForm = new MainForm(getBrowser());
        mainForm.logIn(login, password);
        mainForm.searchProduct(product);

        ResultOfSearchingForm resultOfSearchingForm = new ResultOfSearchingForm(getBrowser());
        Product randomProduct = resultOfSearchingForm.selectRandomProduct();
        getBrowser().goToUrl(randomProduct.getLink());

        ProductPageForm productPageForm = new ProductPageForm(getBrowser(), randomProduct.getName());
        String finalPrice = productPageForm.addProductToTrash(0);
        randomProduct.setCost(finalPrice);
        productPageForm.openTrash();

        TrashForm trashForm = new TrashForm(getBrowser());
        List<Product> productListInTrash = trashForm.getProductsFromTrash();

        Assert.assertEquals(
                productListInTrash.get(0).getName(),
                randomProduct.getName(),
                "Product's name in the trash is not equal with product which was added in trash");
        Assert.assertEquals(
                productListInTrash.get(0).getDescription(),
                randomProduct.getDescription(),
                "Product's description in the trash is not equal with product which was added in trash");
        Assert.assertEquals(
                productListInTrash.get(0).getCost(),
                randomProduct.getCost(),
                "Product's cost in the trash is not equal with product which was added in trash");
    }
}

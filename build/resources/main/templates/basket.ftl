<#include "base.ftl">

<hr>

<p><form action="/return"><input type="submit" value="Return to the list"></form></p>

    <h1 align=center>Products in your basket:</h1>

        <#if productsList?size gt 0>
            <div>
                <Table><tr><th>Item</th><th>Price</th><th>Amount</th><th colspan="2">Action</th><th>Cost</th></tr>
                    <#list productsList as item>
                        <tr><td>${item.productName}</td> <td>${item.outputProductPrice}</td>
                        <td align="center">${item.productAmount}</td>
<td><form action="/${item.productId}/add/basket"><input type="submit" value="+"></form></td>
<td><form action="/${item.productId}/remove/basket"><input type="submit" value="-"></formgradle bootRun></td>
<td align="center">${(item.productPrice * item.productAmount)/100}</td>
                             </tr>
                    </#list>
                    <tr><td><b>${total.productName}</b></td><td></td><td align="center"><b>${total.productAmount}</b></td><td></td><td></td><td align="center"><b>${total.outputProductPrice}</b></td></tr>
                </Table>
            </div>
        </#if>

    </body>
</html>
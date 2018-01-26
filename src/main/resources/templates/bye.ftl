<!doctype html>
<html>
    <head>
    </head>
    <body>
    <h1 align=center>Your order:</h1>

        <#if productsList?size gt 0>
            <div>
                <Table><tr><th>Item</th><th>Price</th><th>Amount</th><th>Cost</th></tr>
                    <#list productsList as item>
                        <tr><td>${item.productName}</td> <td>${item.outputProductPrice}</td>
                        <td align="center">${item.productAmount}</td>
<td align="center">${(item.productPrice * item.productAmount)/100}</td>
                             </tr>
                    </#list>
                    <tr><td><b>${total.productName}</b></td><td></td><td align="center"><b>${total.productAmount}</b></td><td align="center"><b>${total.outputProductPrice}</b></td></tr>
                </Table>
            </div>
        </#if>

        <p><b>${firstName} ${lastName}</b>! Please waiting a call on number ${phoneNumber} from our serviceman!

   </body>
</html>
<#include "base.ftl">

<hr>

<p><form action="/forward"><input type="submit" value="Check the basket"></form></p>
    <h1 align=center>List of available products:</h1>

        <#if productsList?size gt 0>
            <div>
                <Table><tr><th>Item</th><th>Price</th><th>Action</th></tr>
                    <#list productsList as item>
                      <#if item.productAmount gt 0>
                        <tr><td>${item.productName}</td> <td>${item.outputProductPrice}</td>
<td><form action="/${item.productId}/add/list"><input type="submit" value="Add to basket"></form></td>
                             </tr>
                      </#if>
                    </#list>
                </Table>
            </div>
        </#if>


    </body>
</html>
                            
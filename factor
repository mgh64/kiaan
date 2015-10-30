لیست فاکتورها:
db.buy_invoices.aggregate([{$unwind: "$items"},{$group: {_id: "$_id",total: {$sum: {$multiply:["$items.value","$items.price"]}}}}]); 


ریز فاکتور:
db.buy_invoices.aggregate([{$unwind: "$items"},{$project: {_id: 1, total: {$multiply:
["$items.value","$items.price"]}}}]);


db.buy_invoices.aggregate([
    {$unwind: "$items"},
    {$group: {_id: {"_id":"$_id", "number": "$number", "date": "$date", "discount": "$discount"},total: {$sum: {$multiply:["$items.value","$items.price"]}}}},
    {$project: {"_id":0, "date":"$_id.date", "total": {$add: ["$total","$_id.discount"]},"number":"$_id.number"}}
]);


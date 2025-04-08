const express = require('express');
const mongoose = require('mongoose');
const productRoutes = require('./routes/productRoutes');

const app = express();
app.use(express.json());

mongoose.connect('mongodb://mongo:27017/products', {
  useNewUrlParser: true,
  useUnifiedTopology: true
});

app.use('/products', productRoutes);

const PORT = 3001;
app.listen(PORT, () => console.log(`Product Service running on port ${PORT}`));

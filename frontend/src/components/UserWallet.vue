<script setup lang="ts">
import {ref, computed} from 'vue';
import {InputNumber, Dialog} from 'primevue';


const balance = ref(10000.00)
const selectedAsset = ref<Asset>({
    name: '',
    symbol: '',
    value: 0,
    amount: -1
})
interface Asset {
    name: string
    symbol: string
    value: number
    amount: number
}
//TODO: LOAD from backedn
const assets = ref<Asset[]>([
    { name: 'Bitcoin',  symbol: 'BTC', value: 65432.10, amount: 0.5 },
    { name: 'Ethereum', symbol: 'ETH', value: 3456.78, amount: 2.5 },
    { name: 'Solana', symbol: 'SOL', value: 123.45, amount: 10 },
    { name: 'Solana', symbol: 'SOL', value: 123.45, amount: 10 },
    { name: 'Solana', symbol: 'SOL', value: 123.45, amount: 10 },
    { name: 'Solana', symbol: 'SOL', value: 123.45, amount: 10 },
    { name: 'Solana', symbol: 'SOL', value: 123.45, amount: 10 },
])

const getAssets = () => {

}

const toggleSellDialog = () => {

}

const onSellButon = () => {
    console.log(sellAmount.value)
}

const isSelected = (asset: Asset) => {
    return asset == selectedAsset.value
}

const setSelected = (asset: Asset) => {
    selectedAsset.value = asset
    //console.log("Selected:", selected.value)
}

// maybe init to 0 for safety
const sellAmount = ref()
const disableSellInput = computed(()=>{
    return false
    // TODO: ADAPT FOR TESTING VS PRODUCTION
    return selectedAsset.value.amount <= 0
})

</script>

<template>
    <div class="balance-row">
        <p class="balance-value">Your balance: {{balance}} </p>
        <InputNumber v-model="sellAmount" locale="de-DE" :disabled="disableSellInput" 
        :maxFractionDigits="20" class="sell-input"
        :pt="{root: {class: 'empty-class'},
            pcInputText: {class: 'example-class', root: {style: 'text-align: center'}}}"/>
        <!-- Note: All of that convoluted code above just to set text-align to center :DDDDD
         I think the behavior is also sort of ill-defined. The passthrough goes to an
         inputText type and in turn you then need to use the pass through values of an inputText
         that where the root in pcInputNumber comes from. 
         You can see that the example-class is not register at all, while the empty-class is added.
         https://primevue.org/inputnumber
         https://primevue.org/inputtext 
         https://github.com/primefaces/primevue/issues/5814
         :inputStyle="{ textAlign: 'center' }" does the exact same -->
        <button @click="onSellButon" class="primary-button sell"> Sell </button>
    </div>
    <div class="assets-table">
        <div class="assets-table-header">
            <div class="assets-table-cell"> Name </div> 
            <div class="assets-table-cell"> Symbol</div>  
            <div class="assets-table-cell"> Value </div> 
            <div class="assets-table-cell"> Amount </div>  
        </div>
        <div v-for="asset in assets" @click="setSelected(asset)" class="assets-table-row" v-bind:class="{selected: isSelected(asset)}">
            <div class="assets-table-cell"> {{ asset.name }} </div> 
            <div class="assets-table-cell"> {{ asset.symbol }} </div>  
            <div class="assets-table-cell"> {{ asset.value }} </div> 
            <div class="assets-table-cell"> {{ asset.amount }} </div>  
        </div>
    </div>
</template>

<style scoped>
@import '../assets/base.css';
.balance-row{
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
    /* no auto complete
    TODO: look for extension or sth to fix this :(((((((((((((((((((( 
     */
    background-color: var(--color-on-tertiary);
}
.balance-value{
    font-size: large;
}
.sell{
    margin: 8px;
}
/* Look into whether setting class explicitely overrides
the styling from --p-inputnumber */
.sell-input {
    background-color: #fff;
    color: #b91b1b;
    border-radius: 16px;
    padding-inline: 8px;
    text-align: center;
}
.center-input {
    text-align: center;
}
.assets-table{
    display: flex;
    flex-direction: column;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    overflow: hidden;
}
.assets-table-header {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 16px;
    padding: 16px;
    background-color: #3a0568;
    color: white;
    font-weight: 600;
}
.assets-table-row{
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 16px;
    padding: 16px;
    background-color: #9748db;
    border-bottom: 1px solid #f3f4f6;
    transition: background-color 0.6s ease;
    cursor: pointer;
}
.assets-table-row.selected{
    background-color: #3a0568;
}
.assets-table-row:hover {
  background-color: #830e6f;
}
.assets-table-cell{
    justify-self: end;
}
.assets-table-row selected{
    background-color: bisque;
}
</style>